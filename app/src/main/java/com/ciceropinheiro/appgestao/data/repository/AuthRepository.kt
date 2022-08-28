package com.ciceropinheiro.appgestao.data.repository

import androidx.lifecycle.MutableLiveData
import com.ciceropinheiro.appgestao.data.model.User
import com.ciceropinheiro.appgestao.util.UiState

interface AuthRepository {
    fun loginUser(email: String, password: String, result: (UiState<String>) -> Unit)
    fun forgotPassword(email: String, result: (UiState<String>) -> Unit)
    fun logout(result: () -> Unit)
    fun registerUser(email: String, senha: String)
    fun getUserProfileInDatabase(liveData: MutableLiveData<User>)
    fun getAllUsers(liveData: MutableLiveData<List<User>>,liveDataProfile: MutableLiveData<User>)
}