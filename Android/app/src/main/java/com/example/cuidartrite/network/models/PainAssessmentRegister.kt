package com.example.cuidartrite.network.models

import com.google.gson.annotations.SerializedName

data class PainAssessmentRegister(
    val dia: String,
    @SerializedName("nivel_dor")
    val nivelDor: Int,
    @SerializedName("parte_corpo")
    val parteCorpo: String
)
