package com.example.cuidartrite.network.api.service

import com.example.cuidartrite.network.models.PainAssessmentRegister
import com.example.cuidartrite.network.models.PainAssessmentRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

interface PainAssessmentService {
    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("dores-diarias/{user_id}")
    suspend fun listarDoresDiarias(@Path("user_id") userId: Int): List<PainAssessmentRegister>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("registra-dor-diaria")
    suspend fun registrarDor(@Body request: PainAssessmentRequest): Map<String, String>
}