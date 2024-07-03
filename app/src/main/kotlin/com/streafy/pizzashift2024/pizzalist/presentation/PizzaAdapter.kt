package com.streafy.pizzashift2024.pizzalist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.streafy.pizzashift2024.R
import com.streafy.pizzashift2024.databinding.ItemPizzaBinding
import com.streafy.pizzashift2024.pizzalist.domain.Pizza
import com.streafy.pizzashift2024.shared.context

class PizzaAdapter(
    private val onClick: (Pizza) -> Unit
) : ListAdapter<Pizza, PizzaAdapter.PizzaViewHolder>(PizzaDiffCallback) {

    class PizzaViewHolder(binding: ItemPizzaBinding) : RecyclerView.ViewHolder(binding.root) {

        private val image = binding.ivImage
        private val name = binding.tvName
        private val description = binding.tvDescription
        private val price = binding.tvPrice

        fun bind(pizza: Pizza) {
            image.load(pizza.imageUri) {
                placeholder(R.drawable.placeholder_pizza)
                crossfade(true)
            }
            name.text = pizza.name
            description.text = pizza.description
            price.text = context.getString(R.string.price, pizza.price)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPizzaBinding.inflate(inflater, parent, false)

        return PizzaViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClick(getItem(position))
        }
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