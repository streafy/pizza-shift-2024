package com.streafy.pizzashift2024.auth.data

import com.streafy.pizzashift2024.auth.data.model.otp.OtpModel
import com.streafy.pizzashift2024.auth.data.model.otp.OtpResponse
import com.streafy.pizzashift2024.auth.data.model.signin.SignInModel
import com.streafy.pizzashift2024.auth.data.model.signin.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/auth/otp")
    suspend fun requestOtpCode(@Body body: OtpModel): OtpResponse

    @POST("/users/signin")
    suspend fun signIn(@Body body: SignInModel): SignInResponse
}