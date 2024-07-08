package com.streafy.pizzashift2024.shared.tokenstorage

interface TokenStorage {

    suspend fun save(token: String)

    suspend fun get(): String?

    suspend fun clear()
}