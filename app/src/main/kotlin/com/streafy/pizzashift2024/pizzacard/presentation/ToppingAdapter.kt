package com.streafy.pizzashift2024.pizzacard.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.streafy.pizzashift2024.R
import com.streafy.pizzashift2024.databinding.ItemToppingBinding
import com.streafy.pizzashift2024.shared.context
import com.streafy.pizzashift2024.shared.pizza.domain.Topping

class ToppingAdapter(
    private val onClick: (Topping) -> Unit
) : RecyclerView.Adapter<ToppingAdapter.ToppingViewHolder>() {

    var selectedIndexes: Set<Int> = emptySet()

    var toppings: List<Topping> = emptyList()
        set(value) {
            DiffUtil.calculateDiff(ToppingsDiffCallback(toppings, value))
                .dispatchUpdatesTo(this)
            field = value
        }

    class ToppingViewHolder(
        private val binding: ItemToppingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            topping: Topping,
            selected: Boolean,
            onClick: (Topping) -> Unit
        ) {
            with(binding) {
                cvTopping.isChecked = selected

                ivImage.load(topping.imageUri) {
                    placeholder(R.drawable.placeholder_pizza)
                    crossfade(true)
                }
                tvName.text = topping.name
                tvPrice.text = context.getString(R.string.price, topping.price)
            }
            itemView.setOnClickListener {
                onClick(topping)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToppingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemToppingBinding.inflate(inflater, parent, false)

        return ToppingViewHolder(binding)
    }

    override fun getItemCount(): Int = toppings.size

    override fun onBindViewHolder(holder: ToppingViewHolder, position: Int) {
        val topping = toppings[position]
        holder.bind(
            topping = topping,
            selected = topping.id in selectedIndexes,
            onClick = {
                onClick(it)
                notifyItemChanged(position)
            }
        )
    }
}

private class ToppingsDiffCallback(
    private val oldList: List<Topping>,
    private val newList: List<Topping>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem == newItem
    }
}