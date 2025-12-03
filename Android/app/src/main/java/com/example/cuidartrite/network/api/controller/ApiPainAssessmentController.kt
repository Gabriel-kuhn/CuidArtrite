package com.example.cuidartrite.network.api.controller

import android.util.Log
import com.example.cuidartrite.network.api.RetrofitProvider
import com.example.cuidartrite.network.api.service.PainAssessmentService
import com.example.cuidartrite.network.models.PainAssessmentRegister
import com.example.cuidartrite.network.models.PainAssessmentRequest

class ApiPainAssessmentController {

    suspend fun listarDoresDiarias(userId: Int): List<PainAssessmentRegister>? {
        val service = RetrofitProvider.getRetrofit().create(PainAssessmentService::class.java)
        return try {
            service.listarDoresDiarias(userId)
        } catch (e: Exception) {
            Log.e("ApiPainAssessmentController", "Erro ao buscar dores diárias: ${e.message}", e)
            null
        }
    }

    suspend fun registrarDor(request: PainAssessmentRequest): Boolean {
        val service = RetrofitProvider.getRetrofit().create(PainAssessmentService::class.java)
        return try {
            service.registrarDor(request)
            true
        } catch (e: Exception) {
            Log.e("ApiPainAssessmentController", "Erro ao registrar dor: ${e.message}", e)
            false
        }
    }
}