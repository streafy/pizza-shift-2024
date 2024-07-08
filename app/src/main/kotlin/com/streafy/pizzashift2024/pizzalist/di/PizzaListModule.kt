package com.streafy.pizzashift2024.pizzalist.di

import com.streafy.pizzashift2024.pizzalist.data.PizzaListApi
import com.streafy.pizzashift2024.pizzalist.data.PizzaListRepositoryImpl
import com.streafy.pizzashift2024.pizzalist.domain.PizzaListRepository
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
abstract class PizzaListModule {

    companion object {
        @Provides
        @Singleton
        fun providePizzaListApi(
            retrofit: Retrofit
        ): PizzaListApi = retrofit.create()
    }

    @Binds
    abstract fun bindPizzaListRepository(
        repositoryImpl: PizzaListRepositoryImpl
    ): PizzaListRepository
}