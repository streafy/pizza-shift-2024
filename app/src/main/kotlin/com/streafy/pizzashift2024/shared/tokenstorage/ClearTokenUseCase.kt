package com.streafy.pizzashift2024.shared.tokenstorage

import javax.inject.Inject

class ClearTokenUseCase @Inject constructor(
    tokenStorage: TokenStorage
) : suspend () -> Unit by tokenStorage::clear