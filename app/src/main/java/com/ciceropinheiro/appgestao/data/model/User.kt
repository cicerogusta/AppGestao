package com.ciceropinheiro.appgestao.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class User(
    var id: String = "",
    var nomeCompleto : String = "",
    val nivelUsuario: String = "",
    val email: String = "",

    )