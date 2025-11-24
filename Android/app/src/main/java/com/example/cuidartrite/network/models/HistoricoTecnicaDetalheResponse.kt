package com.example.cuidartrite.network.models

import com.google.gson.annotations.SerializedName

data class HistoricoTecnicaDetalheResponse(
    val id: Int,
    @SerializedName("titulo_tecnica")
    val tituloTecnica: String,
    val data: String,
    @SerializedName("nivel_dor_antes")
    val nivelDorAntes: Int,
    @SerializedName("nivel_dor_depois")
    val nivelDorDepois: Int,
    val sensacao: String
)
