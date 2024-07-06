package com.streafy.pizzashift2024.auth.domain

import javax.inject.Inject

class RequestOtpCodeUseCase @Inject constructor(
    repository: AuthRepository
) : suspend (String) -> Int by repository::requestOtpCode