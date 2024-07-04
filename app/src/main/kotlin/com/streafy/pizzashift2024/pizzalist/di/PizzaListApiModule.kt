package com.streafy.pizzashift2024.pizzalist.di

import com.streafy.pizzashift2024.pizzalist.data.PizzaListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PizzaListApiModule {

    @Provides
    @Singleton
    fun providePizzaListApi(
        retrofit: Retrofit
    ): PizzaListApi = retrofit.create()
}