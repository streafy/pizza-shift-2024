package com.streafy.pizzashift2024.shared.token.data.datasource

interface TokenLocalDataSource {

    suspend fun save(token: String)

    suspend fun get(): String?

    suspend fun clear()
}