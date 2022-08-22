package com.ciceropinheiro.appgestao.data.repository

import androidx.lifecycle.MutableLiveData
import com.ciceropinheiro.appgestao.data.model.SignUpUser
import com.ciceropinheiro.appgestao.data.model.User
import com.ciceropinheiro.appgestao.util.UiState
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference

interface AuthRepository {
    fun loginUser(email: String, password: String, result: (UiState<String>) -> Unit)
    fun forgotPassword(email: String, result: (UiState<String>) -> Unit)
    fun logout(result: () -> Unit)
    fun setUserProfileInDatabase(user: User)
    fun getUserProfileInDatabase(liveData: MutableLiveData<User>)
}