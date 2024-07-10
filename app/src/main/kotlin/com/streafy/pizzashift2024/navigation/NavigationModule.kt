package com.streafy.pizzashift2024.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.streafy.pizzashift2024.auth.navigation.AuthRouter
import com.streafy.pizzashift2024.main.MainRouter
import com.streafy.pizzashift2024.navigation.featurerouter.AuthRouterImpl
import com.streafy.pizzashift2024.navigation.featurerouter.MainRouterImpl
import com.streafy.pizzashift2024.navigation.featurerouter.OrdersRouterImpl
import com.streafy.pizzashift2024.navigation.featurerouter.PizzaListRouterImpl
import com.streafy.pizzashift2024.orders.navigation.OrdersRouter
import com.streafy.pizzashift2024.pizzalist.navigation.PizzaListRouter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    companion object {
        @Provides
        @Singleton
        fun provideCicerone() = Cicerone.create()

        @Provides
        @Singleton
        fun provideRouter(cicerone: Cicerone<Router>) = cicerone.router

        @Provides
        @Singleton
        fun provideNavigatorHolder(cicerone: Cicerone<Router>) = cicerone.getNavigatorHolder()
    }

    @Binds
    abstract fun bindGlobalRouter(globalRouterImpl: GlobalRouterImpl): GlobalRouter

    @Binds
    abstract fun bindMainRouter(mainRouterImpl: MainRouterImpl): MainRouter

    @Binds
    abstract fun bindAuthRouter(authRouterImpl: AuthRouterImpl): AuthRouter

    @Binds
    abstract fun bindOrdersRouter(ordersRouterImpl: OrdersRouterImpl): OrdersRouter

    @Binds
    abstract fun pizzaListRouter(pizzaListRouterImpl: PizzaListRouterImpl): PizzaListRouter
}