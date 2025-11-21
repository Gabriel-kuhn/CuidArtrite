package com.example.cuidartrite.network.api.controller

import android.util.Log
import com.example.cuidartrite.network.api.RetrofitProvider
import com.example.cuidartrite.network.api.service.PainAssessmentService
import com.example.cuidartrite.network.models.PainAssessmentRegister

class ApiPainAssessmentController {

    suspend fun listarDoresDiarias(userId: Int): List<PainAssessmentRegister>? {
        val service = RetrofitProvider.getRetrofit().create(PainAssessmentService::class.java)
        return try {
            service.listarDoresDiarias(userId)
        } catch (e: Exception) {
            Log.e("ApiTecnicaController", "Erro ao buscar técnica: ${e.message}", e)
            null
        }
    }
}