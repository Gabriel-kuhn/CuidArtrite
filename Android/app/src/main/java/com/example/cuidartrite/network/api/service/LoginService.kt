package com.example.gerenciadordetc.network.api.service

import com.example.gerenciadordetc.database.User
import com.example.gerenciadordetc.network.models.LoginRequest
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("login")
    suspend fun login(@Body request: LoginRequest): User
}