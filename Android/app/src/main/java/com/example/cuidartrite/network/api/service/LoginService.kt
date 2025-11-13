package com.example.cuidartrite.network.api.service

import com.example.cuidartrite.network.models.LoginRequest
import com.example.cuidartrite.network.models.User
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("login")
    suspend fun login(@Body request: LoginRequest): User
}