package com.streafy.pizzashift2024.auth.data

import com.streafy.pizzashift2024.auth.data.model.otp.OtpModel
import com.streafy.pizzashift2024.auth.data.model.signin.SignInModel
import com.streafy.pizzashift2024.auth.domain.AuthRepository
import com.streafy.pizzashift2024.shared.network.AuthTokenInterceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val authTokenInterceptor: AuthTokenInterceptor
) : AuthRepository {

    override suspend fun requestOtpCode(phone: String) =
        api.requestOtpCode(OtpModel(phone)).retryDelay

    //TODO: add saving token to shared preferences
    override suspend fun signIn(phone: String, code: Int) {
        val signInResponse = api.signIn(SignInModel(phone, code))
        authTokenInterceptor.addToken(signInResponse.token)
    }

}