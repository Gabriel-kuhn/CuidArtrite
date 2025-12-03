package com.example.cuidartrite.network.models

import com.google.gson.annotations.SerializedName

data class PainAssessmentRequest(
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("nivel_dor")
    val nivelDor: Int,
    @SerializedName("descricao")
    val descricao: String,
    @SerializedName("localizacao_dor")
    val localizacaoDor: String
)

