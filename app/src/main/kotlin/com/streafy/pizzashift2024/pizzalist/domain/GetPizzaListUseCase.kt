package com.streafy.pizzashift2024.pizzalist.domain

import com.streafy.pizzashift2024.shared.pizza.domain.Pizza
import javax.inject.Inject

class GetPizzaListUseCase @Inject constructor(
    repository: PizzaListRepository
) : suspend () -> List<Pizza> by repository::getPizzaList