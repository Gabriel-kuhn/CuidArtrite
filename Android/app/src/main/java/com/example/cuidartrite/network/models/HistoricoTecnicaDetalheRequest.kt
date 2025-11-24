package com.example.cuidartrite.network.models

import com.google.gson.annotations.SerializedName

data class HistoricoTecnicaDetalheRequest(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("technique_id")
    val techniqueId: Int
)