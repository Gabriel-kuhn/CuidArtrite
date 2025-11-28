package com.example.cuidartrite.network.api.service

import com.example.cuidartrite.network.models.HistoricoTecnicaDetalheRequest
import com.example.cuidartrite.network.models.HistoricoTecnicaDetalheResponse
import com.example.cuidartrite.network.models.RegistrarAtividadeRequest
import com.example.cuidartrite.network.models.TecnicaDetalheResponse
import com.example.cuidartrite.network.models.TecnicaResumidaDetalheRespose
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TecnicaService {

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("tecnica/{id}")
    suspend fun buscarTecnica(
        @Path("id") id: Int
    ): TecnicaDetalheResponse

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("lista-tecnica/{tipo_id}")
    suspend fun listarTecnicas(
        @Path("tipo_id") tipoId: Int
    ): List<TecnicaDetalheResponse>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("resumo-tecnicas/{tipo_id}")
    suspend fun listarTecnicasResumido(
        @Path("tipo_id") userId: Int
    ): List<TecnicaResumidaDetalheRespose>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("registra-atividade")
    suspend fun registrarAtividade(
        @Body request: RegistrarAtividadeRequest
    ): TecnicaDetalheResponse

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("historico-tecnica/{user_id}/{technique_id}")
    suspend fun getHistoricoTecnica(
        @Path("user_id") userId: Int,
        @Path("technique_id") techniqueId: Int
    ): List<HistoricoTecnicaDetalheResponse>
}