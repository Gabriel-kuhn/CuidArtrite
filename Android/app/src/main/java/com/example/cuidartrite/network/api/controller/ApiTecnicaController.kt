package com.example.cuidartrite.network.api.controller

import android.util.Log
import com.example.cuidartrite.enums.TechiniqueType
import com.example.cuidartrite.network.api.RetrofitProvider
import com.example.cuidartrite.network.api.service.TecnicaService
import com.example.cuidartrite.network.models.HistoricoTecnicaDetalheRequest
import com.example.cuidartrite.network.models.HistoricoTecnicaDetalheResponse
import com.example.cuidartrite.network.models.RegistrarAtividadeRequest
import com.example.cuidartrite.network.models.TecnicaDetalheResponse
import com.example.cuidartrite.network.models.TecnicaResumidaDetalheRespose

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

    suspend fun listarTecnicas(tipoId: TechiniqueType): List<TecnicaDetalheResponse>? {
        val service = RetrofitProvider.getRetrofit().create(TecnicaService::class.java)
        return try {
            service.listarTecnicas(tipoId.id)
        } catch (e: Exception) {
            Log.e("ApiListaTecnicaController", "Erro ao buscar técnicas: ${e.message}", e)
            null
        }
    }


    suspend fun listarTecnicasResumidas(userId: Int): List<TecnicaResumidaDetalheRespose>? {
        val service = RetrofitProvider.getRetrofit().create(TecnicaService::class.java)
        return try {
            service.listarTecnicasResumido(userId)
        } catch (e: Exception) {
            Log.e("ApiListaTecnicaController", "Erro ao buscar técnicas Resumidas: ${e.message}", e)
            null
        }
    }

    suspend fun registrarAtividade(request: RegistrarAtividadeRequest): TecnicaDetalheResponse? {
        val service = RetrofitProvider.getRetrofit().create(TecnicaService::class.java)
        return try {
            service.registrarAtividade( request)
        } catch (e: Exception) {
            Log.e("ApiTecnicaAtividade", "Erro ao registrar atividade: ${e.message}", e)
            null
        }
    }

    suspend fun getHistoricoTecnica(request: HistoricoTecnicaDetalheRequest): List<HistoricoTecnicaDetalheResponse>? {
        val service = RetrofitProvider.getRetrofit().create(TecnicaService::class.java)
        return try {
            service.getHistoricoTecnica(request.userId, request.techniqueId)
        } catch (e: Exception) {
            Log.e("ApiTecnicaController", "Erro ao buscar técnica: ${e.message}", e)
            null
        }
    }
}
