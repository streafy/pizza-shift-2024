package com.streafy.pizzashift2024.shared

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthTokenInterceptor @Inject constructor() : Interceptor {

    private var token: String? = null

    fun addToken(token: String) {
        this.token = token
    }

    fun clearToken() {
        token = null
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        if (token == null) {
            return chain.proceed(chain.request())
        }

        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()

        return chain.proceed(newRequest)
    }
}