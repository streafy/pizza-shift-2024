package com.streafy.pizzashift2024.shared.token.data

import com.streafy.pizzashift2024.shared.token.data.datasource.TokenLocalDataSource
import com.streafy.pizzashift2024.shared.token.domain.TokenRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRepositoryImpl @Inject constructor(
    private val localDataSource: TokenLocalDataSource
) : TokenRepository {

    override suspend fun save(token: String) {
        localDataSource.save(token)
    }

    override suspend fun get(): String? =
        localDataSource.get()

    override suspend fun clear() {
        localDataSource.clear()
    }
}