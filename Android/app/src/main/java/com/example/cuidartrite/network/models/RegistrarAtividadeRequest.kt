package com.example.cuidartrite.network.models

import com.google.gson.annotations.SerializedName

data class RegistrarAtividadeRequest(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("tecnica_id")
    val tecnicaId: Int,
    @SerializedName("nivel_dor_antes")
    val nivelDorAntes: Int,
    @SerializedName("nivel_dor_apos")
    val nivelDorApos: Int,
    @SerializedName("descricao_sensacao")
    val descricaoSensacao: String
)