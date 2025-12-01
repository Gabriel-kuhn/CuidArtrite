package com.example.cuidartrite.network.api.controller

import android.util.Log
import com.example.cuidartrite.network.api.RetrofitProvider
import com.example.cuidartrite.network.api.service.ExportService

class ApiExportController {

    suspend fun downloadPains(): String? {
        val service = RetrofitProvider.getRetrofit().create(ExportService::class.java)
        return try {
            val response = service.downloadPains()
            if (response.isSuccessful) {
                response.body()?.string()
            } else {
                Log.e("ApiExportController", "Erro ao baixar dados: ${response.code()}")
                null
            }
        } catch (e: Exception) {
            Log.e("ApiExportController", "Erro ao baixar dados: ${e.message}", e)
            null
        }
    }
}

