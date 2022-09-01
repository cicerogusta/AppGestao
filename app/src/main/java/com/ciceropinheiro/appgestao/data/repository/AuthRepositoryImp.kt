package com.ciceropinheiro.appgestao.data.repository

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.ciceropinheiro.appgestao.data.model.User
import com.ciceropinheiro.appgestao.util.UiState
import com.example.firebasewithmvvm.util.SharedPrefConstants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener


class AuthRepositoryImp(
    val auth: FirebaseAuth,
    val appPreferences: SharedPreferences,
    val database: DatabaseReference
) : AuthRepository {

    override fun loginUser(
        email: String,
        password: String,
        result: (UiState<String>) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
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
        appPreferences.edit().putString(SharedPrefConstants.USER_SESSION, null).apply()
        result.invoke()
    }


    override fun registerUser(email: String, senha: String) {
        auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener {
            if (it.isSuccessful) {
                val firebaseUser = auth.currentUser
                val teste = User("1", "teste", "aaa", "teste@gmail.com")
                val creuza = User("2", "creuza", "bbb", "creuza@gmail.com")
                val antonio = User("3", "antonio", "ccc", "antonio@gmail.com")
                database.child(firebaseUser!!.uid).setValue(teste)
                database.child(firebaseUser.uid).setValue(creuza)
                database.child(firebaseUser.uid).setValue(antonio)

            }
        }


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

    override fun getAllUsers(
        liveDataAllUsers: MutableLiveData<MutableList<User>>,
        liveDataProfile: MutableLiveData<User>
    ) {
        database.addValueEventListener(object : ValueEventListener {
            val listUsuarios = mutableListOf<User>()


            override fun onDataChange(snapshot: DataSnapshot) {

                for (users in snapshot.children) {
                    users.getValue(User::class.java)?.let { listUsuarios.add(it) }

                }
                liveDataAllUsers.value = listUsuarios

            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }


}