package com.ciceropinheiro.appgestao.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    var id: String = "",
    val nomeCompleto : String = "",
    val nivelUsuario: String = "",
    val email: String = "",
) : Parcelable