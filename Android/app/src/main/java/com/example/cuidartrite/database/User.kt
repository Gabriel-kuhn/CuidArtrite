package com.example.gerenciadordetc.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val nome: String,
    val tipo: Int,
    val email: String,
    val matricula: Int
) : Parcelable
