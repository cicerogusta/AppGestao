package com.ciceropinheiro.appgestao.ui.auth

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ciceropinheiro.appgestao.data.model.User
import com.ciceropinheiro.appgestao.data.repository.AuthRepository
import com.ciceropinheiro.appgestao.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository

) : ViewModel() {

    private val _login = MutableLiveData<UiState<String>>()
    val login: LiveData<UiState<String>>
        get() = _login

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private val _forgotPassword = MutableLiveData<UiState<String>>()
    val forgotPassword: LiveData<UiState<String>>
        get() = _forgotPassword

    fun login(
        email: String,
        password: String
    ) {
        _login.value = UiState.Loading
        repository.loginUser(
            email,
            password
        ) {
            _login.value = it
        }
    }

    fun forgotPassword(email: String) {
        _forgotPassword.value = UiState.Loading
        repository.forgotPassword(email) {
            _forgotPassword.value = it
        }
    }

    fun logout(result: () -> Unit) {
        repository.logout(result)
    }

    fun registerUser(email: String, senha: String) {
        repository.registerUser(email, senha)
    }

    fun getUserProfile() {
        repository.getUserProfileInDatabase(_user)
    }

    fun getAllUser() {
        repository.getAllUsers(_users, _user)
    }


    fun startTwitter(context: Context): Intent? {
        var intent: Intent?
        try {
            // get the Twitter app if possible
            context.packageManager.getPackageInfo("com.twitter.android", 0)
            intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("twitter://user?screen_name=manoeljunior")
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        } catch (e: Exception) {
            // no Twitter app, revert to browser
            intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/manoeljunior"))
        }
        if (intent != null) {
            startActivity(context, intent, null)
        }
        return intent
    }

    fun startInstagram(context: Context): Intent? {
        var intent: Intent? = null
        try {
            // get the Twitter app if possible
            context.packageManager.getPackageInfo("com.instagram.android", 0)
            intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("instagram://user?screen_name=PMPFoficial")
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        } catch (e: Exception) {
            // no Twitter app, revert to browser
            intent =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/prefpedrasdefogo"))
        }
        if (intent != null) {
            startActivity(context, intent, null)
        }
        return intent
    }

    fun startFacebook(context: Context): Intent? {
        var intent: Intent? = null
        try {
            // get the Twitter app if possible
            context.packageManager.getPackageInfo("com.facebook.android", 0)
            intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("instagram://user?screen_name=prefeituradepedrasdefogo")
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            return intent
        } catch (e: Exception) {
            // no Twitter app, revert to browser
            intent =
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://facebook.com/prefeituradepedrasdefogo")
                )
        }
        if (intent != null) {
            startActivity(context, intent, null)
        }
        return intent
    }
}
