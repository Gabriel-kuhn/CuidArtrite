package com.example.cuidartrite.network.api.controller

import android.util.Log
import com.example.cuidartrite.network.api.RetrofitProvider
import com.example.cuidartrite.network.api.service.LoginService
import com.example.cuidartrite.network.models.LoginRequest
import com.example.cuidartrite.network.models.User
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