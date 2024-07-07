package com.streafy.pizzashift2024.auth.data.model.otp

import kotlinx.serialization.Serializable

@Serializable
data class OtpResponse(
    val success: Boolean,
    val reason: String? = null,
    val retryDelay: Int
)
