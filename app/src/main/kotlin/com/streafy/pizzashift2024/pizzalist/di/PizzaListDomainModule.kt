package com.streafy.pizzashift2024.pizzalist.di

import com.streafy.pizzashift2024.pizzalist.domain.GetPizzaListUseCase
import com.streafy.pizzashift2024.pizzalist.domain.PizzaListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PizzaListDomainModule {

    @Provides
    fun provideGetPizzaListUseCase(
        repository: PizzaListRepository
    ): GetPizzaListUseCase = GetPizzaListUseCase(repository)
}