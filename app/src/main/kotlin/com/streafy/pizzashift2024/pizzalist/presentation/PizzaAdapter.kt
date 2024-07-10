package com.streafy.pizzashift2024.pizzalist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.streafy.pizzashift2024.R
import com.streafy.pizzashift2024.databinding.ItemPizzaBinding
import com.streafy.pizzashift2024.shared.context
import com.streafy.pizzashift2024.shared.pizza.domain.Pizza

class PizzaAdapter(
    private val onClick: (Pizza) -> Unit
) : ListAdapter<Pizza, PizzaAdapter.PizzaViewHolder>(PizzaDiffCallback) {

    class PizzaViewHolder(
        private val binding: ItemPizzaBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            pizza: Pizza,
            onClick: (Pizza) -> Unit
        ) {
            with(binding) {
                ivImage.load(pizza.imageUri) {
                    placeholder(R.drawable.placeholder_pizza)
                    crossfade(true)
                }
                tvName.text = pizza.name
                tvDescription.text = pizza.description
                tvPrice.text = context.getString(R.string.price_from, pizza.sizes.first().price)
            }
            itemView.setOnClickListener {
                onClick(pizza)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPizzaBinding.inflate(inflater, parent, false)

        return PizzaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        holder.bind(
            pizza = getItem(position),
            onClick = onClick
        )
    }
}

private object PizzaDiffCallback : DiffUtil.ItemCallback<Pizza>() {
    override fun areItemsTheSame(oldItem: Pizza, newItem: Pizza): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pizza, newItem: Pizza): Boolean {
        return oldItem == newItem
    }
}