package com.ciceropinheiro.appgestao.data.repository

import android.content.SharedPreferences
import com.ciceropinheiro.appgestao.data.repository.AuthRepository
import com.ciceropinheiro.appgestao.data.model.User
import com.example.firebasewithmvvm.util.FireStoreCollection
import com.example.firebasewithmvvm.util.SharedPrefConstants
import com.ciceropinheiro.appgestao.util.UiState
import com.google.firebase.auth.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

class AuthRepositoryImp(
    val auth: FirebaseAuth,
    val database: FirebaseDatabase,
    val appPreferences: SharedPreferences,
) : AuthRepository {

    override fun loginUser(
        email: String,
        password: String,
        result: (UiState<String>) -> Unit) {
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result.invoke(UiState.Success("Logado com Sucesso"))
                }
            }.addOnFailureListener {
                result.invoke(UiState.Failure("Falha ao logar, verifique email e senha"))
            }
    }

    override fun forgotPassword(email: String, result: (UiState<String>) -> Unit) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    result.invoke(UiState.Success("Email has been sent"))

                } else {
                    result.invoke(UiState.Failure(task.exception?.message))
                }
            }.addOnFailureListener {
                result.invoke(UiState.Failure("Authentication failed, Check email"))
            }
    }

    override fun logout(result: () -> Unit) {
        auth.signOut()
        appPreferences.edit().putString(SharedPrefConstants.USER_SESSION,null).apply()
        result.invoke()
    }

    override fun setUserInDatabase(user: User) {
        database.reference.child("Users").setValue(user)
    }

    override fun getUserInDatabase(): FirebaseUser? {
        return auth.currentUser
    }

}