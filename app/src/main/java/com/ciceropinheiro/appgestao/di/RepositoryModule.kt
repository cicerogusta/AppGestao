package com.ciceropinheiro.appgestao.di

import android.content.SharedPreferences
import com.ciceropinheiro.appgestao.data.repository.AuthRepository
import com.ciceropinheiro.appgestao.data.repository.AuthRepositoryImp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAutghRepository(
        database: FirebaseDatabase,
        auth: FirebaseAuth,
        appPreferences: SharedPreferences,
    ): AuthRepository {
        return AuthRepositoryImp(auth,database,appPreferences)
    }
}