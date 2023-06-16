package com.hitechtalents.assessment.presentation.di

import android.content.Context
import android.content.SharedPreferences
import com.hitechtalents.assessment.data.ProfileRepositoryImpl
import com.hitechtalents.assessment.domain.repository.ProfileRepository
import com.hitechtalents.assessment.domain.usecases.GetProfile
import com.hitechtalents.assessment.domain.usecases.ProfileUseCases
import com.hitechtalents.assessment.domain.usecases.SignUp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("session_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(sharedPreferences: SharedPreferences): ProfileRepository {
        return ProfileRepositoryImpl(sharedPreferences)
    }

    @Provides
    fun provideProfileUseCases(profileRepository: ProfileRepository): ProfileUseCases {
        return ProfileUseCases(
            signUp = SignUp(profileRepository),
            getProfile = GetProfile(profileRepository)
        )
    }
}