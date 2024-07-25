package com.streafy.pizzashift2024.shared.token.domain.usecase

import com.streafy.pizzashift2024.shared.token.domain.TokenRepository
import javax.inject.Inject

class ClearTokenUseCase @Inject constructor(
    repository: TokenRepository
) : suspend () -> Unit by repository::clear