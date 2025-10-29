package com.example.gerenciadordetc.network.api.controller

import android.util.Log
import com.example.gerenciadordetc.database.User
import com.example.gerenciadordetc.network.api.RetrofitProvider
import com.example.gerenciadordetc.network.api.service.LoginService
import com.example.gerenciadordetc.network.models.LoginRequest
import kotlinx.coroutines.runBlocking

class ApiLoginController {

    fun login(request: LoginRequest): User? {
        val service = RetrofitProvider.getRetrofit().create(LoginService::class.java)
        return try {
            runBlocking { service.login(request) }
        } catch (e: Exception) {
            Log.e("ApiLoginController", "Erro em login: ${e.message}", e)
            null
        }
    }
}