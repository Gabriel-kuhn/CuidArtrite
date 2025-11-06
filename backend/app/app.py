import json

from flask import Flask, request, jsonify, Response
import mysql.connector
import os
import base64

app = Flask(__name__)

conn = mysql.connector.connect(
    host="localhost",
    user="root",
    password="",
    database="dbCuidArtrite"
)

@app.route("/login", methods=["POST"])
def login():
    try:
        # Get JSON data from request
        data = request.get_json()

        if not data or 'user' not in data or 'password' not in data:
            return Response(
                json.dumps({"error": "Usuário e senha são obrigatórios"}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 400

        username = data['user']
        senha = data['password']

        # Query to get user information
        cursor = conn.cursor(dictionary=True)
        query = """
        SELECT u.id, u.name, u.age, u.gender, u.phone, u.font_size, 
               u.contrast, u.reading_mode, u.allow_data_collection
        FROM users u
        WHERE u.username = %s AND u.password_hash = %s
        """
        cursor.execute(query, (username, senha))
        user = cursor.fetchone()
        cursor.close()

        if not user:
            return Response(
                json.dumps({"error": "Usuário não encontrado"}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 404

        # Return user information
        return Response(
            json.dumps({
                "id": user["id"],
                "nome": user["name"],
                "idade": user["age"],
                "sexo": user["gender"],
                "telefone": user["phone"],
                "tamanho_fonte": user["font_size"],
                "contraste": user["contrast"],
                "leitura_voz": user["reading_mode"],
                "coletar_dados": user["allow_data_collection"]
            }, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 200

    except Exception as e:
        return Response(
            json.dumps({"error": f"Erro interno no servidor: {str(e)}"}, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 500


@app.route("/cadastro", methods=["POST"])
def create_account():
    try:
        # Get JSON data from request
        data = request.get_json()
        
        fields = ["nome", "email", "idade", "sexo", "telefone", "tamanho_fonte", "contraste", "leitura_voz", "coletar_dados", "password"]

        if not data:
            return Response(
                json.dumps({"error": "Os dados da requisição são obrigatórios"}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 400
        
        for field in fields:
            if field not in data or data[field] in [None, ""]:
                return Response(
                    json.dumps({"error": "Os dados da requisição são obrigatórios"}, ensure_ascii=False),
                    content_type="application/json; charset=utf-8"
                ), 400
        
        name = data['nome']
        email = data['email']
        age = data['idade']
        gender = data['sexo']
        phone = data['telefone']
        font_size = data['tamanho_fonte']
        contrast = data['contraste']
        reading_mode = data['leitura_voz']
        allow_data_collection = data['coletar_dados']
        password_hash = data['password']
        
        cursor = conn.cursor(dictionary=True)
        
        # Verificar se o email já existe
        check_query = "SELECT id FROM users WHERE email = %s"
        cursor.execute(check_query, (email,))
        existing_user = cursor.fetchone()
        
        if existing_user:
            cursor.close()
            return Response(
                json.dumps({"error": "Email já cadastrado"}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 409
        
        # Inserir novo usuário
        insert_query = """
        INSERT INTO users 
        (name, email, age, gender, phone, font_size, contrast, reading_mode, allow_data_collection, password_hash)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        """
        cursor.execute(insert_query, (
            name, email, age, gender, phone, font_size, contrast, reading_mode, allow_data_collection, password_hash
        ))
        conn.commit()
        cursor.close()
        
        return Response(
            json.dumps({"message": "Usuário cadastrado com sucesso"}, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 200
        
    except Exception as e:
        return Response(
            json.dumps({"error": f"Erro interno no servidor: {str(e)}"}, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 500


if __name__ == '__main__':
    #app.run(debug=True)
    app.run(host="0.0.0.0", port=5000) # para rodar em lan