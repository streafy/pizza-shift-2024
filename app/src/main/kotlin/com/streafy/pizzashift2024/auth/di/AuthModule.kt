package com.streafy.pizzashift2024.auth.di

import com.streafy.pizzashift2024.auth.data.AuthApi
import com.streafy.pizzashift2024.auth.data.AuthRepositoryImpl
import com.streafy.pizzashift2024.auth.domain.AuthRepository
import com.streafy.pizzashift2024.auth.domain.RequestOtpCodeUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    companion object {
        @Provides
        @Singleton
        fun provideAuthApi(retrofit: Retrofit): AuthApi = retrofit.create()
    }

    @Binds
    abstract fun bindAuthRepository(repositoryImpl: AuthRepositoryImpl): AuthRepository
}