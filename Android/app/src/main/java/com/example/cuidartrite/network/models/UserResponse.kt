package com.example.cuidartrite.network.models

data class UserResponse(
    val id: Int,
    val nome: String,
    val email: String,
    val idade: Int,
    val sexo: String,
    val telefone: String,
    val tamanho_fonte: Int,
    val contraste: Int,
    val leitura_voz: Boolean,
    val coletar_dados: Boolean
)
