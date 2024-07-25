package com.streafy.pizzashift2024.shared.token.domain.usecase

import com.streafy.pizzashift2024.shared.token.domain.TokenRepository
import javax.inject.Inject

class IsTokenExistsUseCase @Inject constructor(
    private val repository: TokenRepository
) {

    suspend operator fun invoke() =
        repository.get() != null
}