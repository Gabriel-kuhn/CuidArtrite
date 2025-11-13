package com.example.cuidartrite.network.api.controller

import android.util.Log
import com.example.cuidartrite.network.api.RetrofitProvider
import com.example.cuidartrite.network.api.service.CadastroService
import com.example.cuidartrite.network.models.User

class ApiCadastroController {

    suspend fun cadastrar(request: User): User? {
        val service = RetrofitProvider.getRetrofit().create(CadastroService::class.java)
        return try {
            service.cadastrar(request)
        } catch (e: Exception) {
            Log.e("ApiCadastroController", "Erro ao cadastrar: ${e.message}", e)
            null
        }
    }
}
