import json
import sys

from flask import Flask, request, jsonify, Response
import mysql.connector
from datetime import datetime

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
        SELECT u.id, u.name, u.username, u.email, u.age, u.gender, u.phone, u.font_size, 
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
                "username": user["username"],
                "nome": user["name"],
                "email": user["email"],
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
        
        fields = ["username", "nome", "email", "idade", "sexo", "telefone", "tamanho_fonte", "contraste", "leitura_voz", "coletar_dados", "password"]

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
        
        username = data['username']
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
        (username, name, email, age, gender, phone, font_size, contrast, reading_mode, allow_data_collection, password_hash)
        VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)
        """
        cursor.execute(insert_query, (
            username, name, email, age, gender, phone, font_size, contrast, reading_mode, allow_data_collection, password_hash
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

@app.route("/lista-tecnica/<int:id>", methods=["GET"])
def get_all_techniques(id):
    try:
        cursor = conn.cursor(dictionary=True)
        query = """
        SELECT t.id, t.title, t.how_long, t.benefits, t.video_url, 
               t.picture_url, t.how_to_do, t.hint, tc.name AS technique_type
        FROM technique AS t
        INNER JOIN technique_type AS tc
        ON t.technique_type_id = tc.id
        WHERE tc.id = %s
        """
        cursor.execute(query, (id,))
        records = cursor.fetchall()
        cursor.close()

        if not records:
            return Response(
                json.dumps([], ensure_ascii=False),  # empty list if nothing found
                content_type="application/json; charset=utf-8"
            ), 200

        # Format results as a list of dicts
        techniques = []
        for record in records:
            techniques.append({
                "id": record["id"],
                "titulo": record["title"],
                "beneficios": record["benefits"],
                "video_url": record["video_url"],
                "imagem_url": record["picture_url"],
                "como_fazer": record["how_to_do"],
                "dica": record["hint"],
                "quanto_tempo": record["how_long"],
                "tipo_tecnica": record["technique_type"]
            })

        return Response(
            json.dumps(techniques, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 200

    except Exception as e:
        return Response(
            json.dumps({"error": f"Erro interno no servidor: {str(e)}"}, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 500

@app.route("/tecnica/<int:id>", methods=["GET"])
def get_technique(id):
    try:
        cursor = conn.cursor(dictionary=True)
        query = """
        SELECT t.id, t.title, t.how_long, t.benefits, t.video_url, 
               t.picture_url, t.how_to_do, t.hint, tc.name AS technique_type
        FROM technique AS t
        INNER JOIN technique_type AS tc
        ON t.technique_type_id = tc.id
        WHERE t.id = %s
        """
        cursor.execute(query, (id,))
        record = cursor.fetchone()
        cursor.close()

        if not record:
            return Response(
                json.dumps({"error": "Técnica não encontrada"}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 404

        # Format and return JSON
        return Response(
            json.dumps({
                "id": record["id"],
                "titulo": record["title"],
                "beneficios": record["benefits"],
                "video_url": record["video_url"],
                "imagem_url": record["picture_url"],
                "como_fazer": record["how_to_do"],
                "dica": record["hint"],
                "quanto_tempo": record["how_long"],
                "tipo_tecnica": record["technique_type"]
            }, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 200

    except Exception as e:
        return Response(
            json.dumps({"error": f"Erro interno no servidor: {str(e)}"}, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 500

@app.route("/registra-atividade", methods=["POST"])
def record_activity():
    try:
        # Get JSON data from request
        data = request.get_json()
        
        fields = ["user_id", "tecnica_id", "nivel_dor_antes", "nivel_dor_apos", "descricao_sensacao"]

        if not data:
            return Response(
                json.dumps({"error": "Os dados da requisição são obrigatórios"}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 400
        
        for field in fields:
            if field not in data or data[field] in [None, ""]:
                return Response(
                    json.dumps({"error": f"O campo '{field}' é obrigatório"}, ensure_ascii=False),
                    content_type="application/json; charset=utf-8"
                ), 400
        
        user_id = data['user_id']
        technique_id = data['tecnica_id']
        initial_pain_scale = data['nivel_dor_antes']
        final_pain_scale = data['nivel_dor_apos']
        sensation_description = data['descricao_sensacao']
        current_date = datetime.now().strftime("%Y-%m-%d %H:%M:%S")  # current timestamp
        
        cursor = conn.cursor(dictionary=True)
        
        # insert new pain record
        insert_query = """
        INSERT INTO technique_history
        (user_id, technique_id, initial_pain_scale, final_pain_scale, sensation_description, date)
        VALUES (%s, %s, %s, %s, %s, %s)
        """
        cursor.execute(insert_query, (
            user_id, technique_id, initial_pain_scale, final_pain_scale, sensation_description, current_date
        ))
        conn.commit()
        cursor.close()
        
        return Response(
            json.dumps({"message": "Atividade registrada com sucesso"}, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 200
        
    except Exception as e:
        return Response(
            json.dumps({"error": f"Erro interno no servidor: {str(e)}"}, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 500
    


@app.route("/historico-tecnicas/<int:id>", methods=["GET"])
def get_technique_history(id):
    try:
        cursor = conn.cursor(dictionary=True)
        query = """
        SELECT th.id, th.date, th.initial_pain_scale, th.final_pain_scale, th.sensation_description, t.title
        FROM technique_history AS th
        INNER JOIN technique AS t
        ON th.technique_id = t.id
        INNER JOIN technique_type AS tt 
        ON t.technique_type_id = tt.id
        WHERE th.user_id = %s
        ORDER BY th.date DESC
        """
        cursor.execute(query, (id,))
        records = cursor.fetchall()
        cursor.close()

        if not records:
            return Response(
                json.dumps({"error": "Histórico de técnicas não encontrado para o usuário."}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 404

        # Format response
        result = []
        for record in records:
            result.append({
                "id": record["id"],
                "titulo_tecnica": record["title"],
                "data": record["date"].strftime("%Y-%m-%d") if record["date"] else None,
                "nivel_dor_antes": record["initial_pain_scale"],
                "nivel_dor_depois": record["final_pain_scale"],
                "sensacao": record["sensation_description"],
            })

        return Response(
            json.dumps(result, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 200

    except Exception as e:
        return Response(
            json.dumps({"error": f"Erro interno no servidor: {str(e)}"}, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 500
    
@app.route("/historico-tecnica", methods=["GET"])
def get_specific_technique_history():
    try:
        # Get JSON data from request
        data = request.get_json()
        
        fields = ["user_id", "technique_id"]

        if not data:
            return Response(
                json.dumps({"error": "Os dados da requisição são obrigatórios"}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 400
        
        for field in fields:
            if field not in data or data[field] in [None, ""]:
                return Response(
                    json.dumps({"error": f"O campo '{field}' é obrigatório"}, ensure_ascii=False),
                    content_type="application/json; charset=utf-8"
                ), 400

        user_id = data['user_id']
        technique_id = data['technique_id']

        cursor = conn.cursor(dictionary=True)
        query = """
        SELECT th.id, th.date, th.initial_pain_scale, th.final_pain_scale, th.sensation_description, t.title
        FROM technique_history AS th
        INNER JOIN technique AS t
        ON th.technique_id = t.id
        INNER JOIN technique_type AS tt 
        ON t.technique_type_id = tt.id
        WHERE th.user_id = %s and t.id = %s
        ORDER BY th.date DESC
        """
        cursor.execute(query, (user_id,technique_id))
        records = cursor.fetchall()
        cursor.close()

        if not records:
            return Response(
                json.dumps({"error": "Histórico de técnicas não encontrado para o usuário."}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 404

        # Format response
        result = []
        for record in records:
            result.append({
                "id": record["id"],
                "titulo_tecnica": record["title"],
                "data": record["date"].strftime("%Y-%m-%d") if record["date"] else None,
                "nivel_dor_antes": record["initial_pain_scale"],
                "nivel_dor_depois": record["final_pain_scale"],
                "sensacao": record["sensation_description"],
            })

        return Response(
            json.dumps(result, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 200

    except Exception as e:
        return Response(
            json.dumps({"error": f"Erro interno no servidor: {str(e)}"}, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 500

@app.route("/registra-dor-diaria", methods=["POST"])
def registra_dor_diaria():
    try:
        # Get JSON data from request
        data = request.get_json()

        fields = ["user_id", "nivel_dor", "descricao", "localizacao_dor"]

        if not data:
            return Response(
                json.dumps({"error": "Os dados da requisição são obrigatórios"}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 400

        for field in fields:
            if field not in data or data[field] in [None, ""]:
                return Response(
                    json.dumps({"error": f"O campo '{field}' é obrigatório"}, ensure_ascii=False),
                    content_type="application/json; charset=utf-8"
                ), 400

        user_id = data['user_id']
        pain_scale = data['nivel_dor']
        # descricao is validated but not stored in pain_assessment table
        localized_pain = data['localizacao_dor']
        current_datetime = datetime.now()
        current_date = current_datetime.strftime("%Y-%m-%d %H:%M:%S")
        current_day = current_datetime.strftime("%Y-%m-%d")  # just the date part

        cursor = conn.cursor(dictionary=True)

        # Verificar se o usuário existe
        check_query = "SELECT id FROM users WHERE id = %s"
        cursor.execute(check_query, (user_id,))
        existing_user = cursor.fetchone()

        if not existing_user:
            cursor.close()
            return Response(
                json.dumps({"error": "Usuário não encontrado"}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 404

        # Verificar se já existe registro de dor para o dia atual
        check_pain_query = """
        SELECT id FROM pain_assessment
        WHERE user_id = %s AND DATE(date) = %s
        """
        cursor.execute(check_pain_query, (user_id, current_day))
        existing_pain = cursor.fetchone()

        if existing_pain:
            # Atualizar registro existente
            update_query = """
            UPDATE pain_assessment
            SET pain_scale = %s, localized_pain = %s, date = %s
            WHERE id = %s
            """
            cursor.execute(update_query, (
                pain_scale, localized_pain, current_date, existing_pain['id']
            ))
            conn.commit()
            cursor.close()

            return Response(
                json.dumps({"message": "Dor diária atualizada com sucesso"}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 200
        else:
            # Inserir novo registro de dor diária
            insert_query = """
            INSERT INTO pain_assessment
            (user_id, pain_scale, localized_pain, date)
            VALUES (%s, %s, %s, %s)
            """
            cursor.execute(insert_query, (
                user_id, pain_scale, localized_pain, current_date
            ))
            conn.commit()
            cursor.close()

            return Response(
                json.dumps({"message": "Dor diária registrada com sucesso"}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 200

    except Exception as e:
        return Response(
            json.dumps({"error": f"Erro interno no servidor: {str(e)}"}, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 500

@app.route("/dores-diarias/<int:user_id>", methods=["GET"])
def get_dores_diarias(user_id):
    try:
        cursor = conn.cursor(dictionary=True)
        query = """
        SELECT p.date, p.pain_scale, p.localized_pain
        FROM pain_assessment AS p
        WHERE p.user_id = %s
        """
        cursor.execute(query, (user_id,))
        records = cursor.fetchall()
        cursor.close()

        if not records:
            return Response(
                json.dumps({"error": "Dores não encontradas"}, ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 404

        pains = []
        for record in records:
            pains.append({
                "dia": record["date"].date().isoformat(),
                "nivel_dor": record["pain_scale"],
                "parte_corpo": record["localized_pain"],
            })

        # Format and return JSON
        return Response(
            json.dumps(pains, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 200

    except Exception as e:
        return Response(
            json.dumps({"error": f"Erro interno no servidor: {str(e)}"}, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 500

@app.route("/resumo-tecnicas/<int:user_id>", methods=["GET"])
def get_technique_summary(user_id):
    try:
        cursor = conn.cursor(dictionary=True)
        query = """
        SELECT
            t.id,
            t.title AS nome,
            COUNT(th.id) AS vezes_praticada,
            MAX(th.date) AS ultima_vez_praticada
        FROM technique AS t
        INNER JOIN technique_history AS th
        ON t.id = th.technique_id
        WHERE th.user_id = %s
        GROUP BY t.id, t.title
        ORDER BY ultima_vez_praticada DESC
        """
        cursor.execute(query, (user_id,))
        records = cursor.fetchall()
        cursor.close()

        if not records:
            return Response(
                json.dumps([], ensure_ascii=False),
                content_type="application/json; charset=utf-8"
            ), 200

        # Format response
        result = []
        for record in records:
            result.append({
                "id": record["id"],
                "nome": record["nome"],
                "vezes_praticada": record["vezes_praticada"],
                "ultima_vez_praticada": record["ultima_vez_praticada"].strftime("%Y-%m-%d %H:%M:%S") if record["ultima_vez_praticada"] else None,
            })

        return Response(
            json.dumps(result, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 200

    except Exception as e:
        return Response(
            json.dumps({"error": f"Erro interno no servidor: {str(e)}"}, ensure_ascii=False),
            content_type="application/json; charset=utf-8"
        ), 500

if __name__ == '__main__':
    port = int(sys.argv[1]) if len(sys.argv) > 1 else 5000
    app.run(host="0.0.0.0", port=port) # para rodar em lan