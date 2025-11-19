package com.example.cuidartrite.network.models

import com.google.gson.annotations.SerializedName

data class TecnicaResumidaDetalheRespose(
    val id: Int,
    @SerializedName("nome")
    val titulo: String,

    @SerializedName("vezes_praticada")
    val vezesPraticada: Int,

    @SerializedName("ultima_vez_praticada")
    val ultimaVezPraticada: String
)