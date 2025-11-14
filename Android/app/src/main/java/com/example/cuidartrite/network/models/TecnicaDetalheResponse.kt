package com.example.cuidartrite.network.models

import com.google.gson.annotations.SerializedName

data class TecnicaDetalheResponse(
    val id: Int,
    val titulo: String,
    val beneficios: String,
    @SerializedName("video_url")
    val videoUrl: String,
    @SerializedName("como_fazer")
    val comoFazer: String,
    val dica: String,
    @SerializedName("quanto_tempo")
    val quantoTempo: Int,
    @SerializedName("tipo_tecnica")
    val tipoRecnica: Int
)
