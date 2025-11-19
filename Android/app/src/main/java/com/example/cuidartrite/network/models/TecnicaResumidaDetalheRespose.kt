package com.example.cuidartrite.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TecnicaResumidaDetalheRespose(
    val id: Int,
    @SerializedName("nome")
    val titulo: String,

    @SerializedName("vezes_praticada")
    val vezesPraticada: Int,

    @SerializedName("ultima_vez_praticada")
    val ultimaVezPraticada: String
) : Parcelable