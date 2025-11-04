package com.example.cuidartrite.network.models

import com.google.gson.annotations.SerializedName

data class RegistrarAtividadeRequest(
    @SerializedName("user_id")
    val userId: Int,
    val tecnica_id: Int,
    val nivel_dor_antes: Int,
    val nivel_dor_apos: Int,
    val descricao_sensacao: String
)