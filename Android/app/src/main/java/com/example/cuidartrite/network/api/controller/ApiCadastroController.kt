package com.example.cuidartrite.network.api.controller

import android.util.Log
import com.example.cuidartrite.network.api.service.CadastroService
import com.example.cuidartrite.network.models.CadastroRequest
import com.example.cuidartrite.network.models.UserResponse
import com.example.gerenciadordetc.network.api.RetrofitProvider

class ApiCadastroController {

    suspend fun cadastrar(request: CadastroRequest): UserResponse? {
        val service = RetrofitProvider.getRetrofit().create(CadastroService::class.java)
        return try {
            service.cadastrar(request)
        } catch (e: Exception) {
            Log.e("ApiCadastroController", "Erro ao cadastrar: ${e.message}", e)
            null
        }
    }
}
