package com.example.gerenciadordetc.network.models

data class LoginRequest(
    val matricula: Int,
    val senha: String
)