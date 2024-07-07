package com.streafy.pizzashift2024.auth.data.model.signin

import kotlinx.serialization.Serializable

@Serializable
data class SignInResponse(
    val success: Boolean,
    val reason: String? = null,
    val user: UserModel,
    val token: String,
)