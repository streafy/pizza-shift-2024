package com.streafy.pizzashift2024.shared.token.domain

interface TokenRepository {

    suspend fun save(token: String)

    suspend fun get(): String?

    suspend fun clear()
}