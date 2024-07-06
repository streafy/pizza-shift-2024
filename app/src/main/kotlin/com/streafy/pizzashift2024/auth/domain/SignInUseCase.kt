package com.streafy.pizzashift2024.auth.domain

import javax.inject.Inject

class SignInUseCase @Inject constructor(
    repository: AuthRepository
) : suspend (String, Int) -> Unit by repository::signIn