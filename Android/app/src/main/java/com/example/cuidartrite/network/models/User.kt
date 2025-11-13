package com.example.cuidartrite.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int?,
    val nome: String,
    val email: String,
    val idade: Int,
    val sexo: String,
    val telefone: String,
    @SerializedName("tamanho_fonte") val tamanhoFonte: Int,
    val contraste: Int,
    @SerializedName("leitura_voz") val leituraVoz: Int,
    @SerializedName("coletar_dados") val coletarDados: Int,
    val password: String?
) : Parcelable
