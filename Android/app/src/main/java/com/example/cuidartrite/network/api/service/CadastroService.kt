package com.example.cuidartrite.network.api.service

import com.example.cuidartrite.network.models.CadastroRequest
import com.example.cuidartrite.network.models.UserResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CadastroService {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("cadastro")
    suspend fun cadastrar(@Body request: CadastroRequest): UserResponse
}
