package com.streafy.pizzashift2024.auth.domain

interface AuthRepository {

    suspend fun requestOtpCode(phone: String): Int

    suspend fun signIn(phone: String, code: Int)
}