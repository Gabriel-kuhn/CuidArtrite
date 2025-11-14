package com.example.cuidartrite.network.api.service

import com.example.cuidartrite.network.models.RegistrarAtividadeRequest
import com.example.cuidartrite.network.models.TecnicaDetalheResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path

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
    @POST("tecnica/{id}/registrar-atividade")
    suspend fun registrarAtividade(
        @Path("id") id: Int,
        @Body request: RegistrarAtividadeRequest
    ): TecnicaDetalheResponse
}