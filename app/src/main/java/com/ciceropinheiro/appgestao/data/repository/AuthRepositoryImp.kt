package com.ciceropinheiro.appgestao.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.ciceropinheiro.appgestao.data.model.SignUpUser
import com.ciceropinheiro.appgestao.data.model.User
import com.ciceropinheiro.appgestao.util.UiState
import com.example.firebasewithmvvm.util.SharedPrefConstants
import com.google.firebase.auth.*
import com.google.firebase.database.*


class AuthRepositoryImp(
    val auth: FirebaseAuth,
    val appPreferences: SharedPreferences,
    val database: DatabaseReference
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



     override fun setUserProfileInDatabase(user: User) {
        database.child(auth.currentUser?.uid.toString()).setValue(user)

    }

     override fun getUserProfileInDatabase(liveData: MutableLiveData<User>) {
        val uid = auth.currentUser?.uid.toString()
        database.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                liveData.postValue(snapshot.getValue(User::class.java))
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }


}