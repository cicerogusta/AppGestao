package com.ciceropinheiro.appgestao.di

import android.content.SharedPreferences
import com.ciceropinheiro.appgestao.data.repository.AuthRepository
import com.ciceropinheiro.appgestao.data.repository.AuthRepositoryImp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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
        auth: FirebaseAuth,
        appPreferences: SharedPreferences,
        database: DatabaseReference
    ): AuthRepository {
        return AuthRepositoryImp(auth, appPreferences, database)
    }
}