package com.streafy.pizzashift2024.pizzalist.data

import com.streafy.pizzashift2024.pizzalist.data.model.PizzaListResponse
import retrofit2.http.GET

interface PizzaListApi {

    @GET("catalog")
    suspend fun getPizzaList(): PizzaListResponse
}