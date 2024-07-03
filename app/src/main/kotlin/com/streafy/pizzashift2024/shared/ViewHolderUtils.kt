package com.streafy.pizzashift2024.shared

import android.content.Context
import androidx.recyclerview.widget.RecyclerView


val RecyclerView.ViewHolder.context: Context
    get() = this.itemView.context