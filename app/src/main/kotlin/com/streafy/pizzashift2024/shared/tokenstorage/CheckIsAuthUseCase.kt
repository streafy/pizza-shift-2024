package com.streafy.pizzashift2024.shared.tokenstorage

import javax.inject.Inject

class CheckIsAuthUseCase @Inject constructor(
    private val tokenStorage: TokenStorage
) {

    suspend operator fun invoke() =
        tokenStorage.get() != null
}