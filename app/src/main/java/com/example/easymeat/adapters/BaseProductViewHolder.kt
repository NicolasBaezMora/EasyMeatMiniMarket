package com.example.easymeat.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseProductViewHolder<T>(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T, position: Int, typeButton: String, context: Context)
}