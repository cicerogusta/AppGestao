package com.ciceropinheiro.appgestao.data.repository

import com.ciceropinheiro.appgestao.data.model.User
import com.ciceropinheiro.appgestao.util.UiState
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    fun loginUser(email: String, password: String, result: (UiState<String>) -> Unit)
    fun forgotPassword(email: String, result: (UiState<String>) -> Unit)
    fun logout(result: () -> Unit)
    fun setUserInDatabase(user: User)
    fun getUserInDatabase(): FirebaseUser?
}