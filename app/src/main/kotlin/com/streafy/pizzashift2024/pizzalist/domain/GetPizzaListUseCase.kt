package com.streafy.pizzashift2024.pizzalist.domain

import javax.inject.Inject

class GetPizzaListUseCase @Inject constructor(
    repository: PizzaListRepository
) : suspend () -> List<Pizza> by repository::getPizzaList