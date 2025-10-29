package com.example.gerenciadordetc.network.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginResponse(
    val nome: String,
    val tipo: Int,
    val email: String,
    val matricula: Int
) : Parcelable