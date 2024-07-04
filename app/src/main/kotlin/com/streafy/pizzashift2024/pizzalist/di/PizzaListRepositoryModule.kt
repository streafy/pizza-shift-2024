package com.streafy.pizzashift2024.pizzalist.di

import com.streafy.pizzashift2024.pizzalist.data.PizzaListRepositoryImpl
import com.streafy.pizzashift2024.pizzalist.domain.PizzaListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PizzaListRepositoryModule {

    @Binds
    abstract fun bindPizzaListRepository(
        repositoryImpl: PizzaListRepositoryImpl
    ): PizzaListRepository
}