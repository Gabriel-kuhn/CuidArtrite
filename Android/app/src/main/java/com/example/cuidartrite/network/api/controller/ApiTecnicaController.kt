package com.example.cuidartrite.network.api.controller

import android.util.Log
import com.example.cuidartrite.network.api.service.TecnicaService
import com.example.cuidartrite.network.models.RegistrarAtividadeRequest
import com.example.cuidartrite.network.models.TecnicaDetalheResponse
import com.example.cuidartrite.network.models.TecnicaResponse
import com.example.cuidartrite.network.api.RetrofitProvider

class ApiTecnicaController {

    suspend fun buscarTecnica(id: Int): TecnicaDetalheResponse? {
        val service = RetrofitProvider.getRetrofit().create(TecnicaService::class.java)
        return try {
            service.buscarTecnica(id)
        } catch (e: Exception) {
            Log.e("ApiTecnicaController", "Erro ao buscar técnica: ${e.message}", e)
            null
        }
    }

    suspend fun listarTecnicas(tipoId: Int): List<TecnicaResponse>? {
        val service = RetrofitProvider.getRetrofit().create(TecnicaService::class.java)
        return try {
            service.listarTecnicas(tipoId)
        } catch (e: Exception) {
            Log.e("ApiListaTecnicaController", "Erro ao buscar técnicas: ${e.message}", e)
            null
        }
    }

    suspend fun registrarAtividade(request: RegistrarAtividadeRequest): TecnicaDetalheResponse? {
        val service = RetrofitProvider.getRetrofit().create(TecnicaService::class.java)
        return try {
            service.registrarAtividade(request.tecnica_id, request)
        } catch (e: Exception) {
            Log.e("ApiTecnicaAtividade", "Erro ao registrar atividade: ${e.message}", e)
            null
        }
    }
}
