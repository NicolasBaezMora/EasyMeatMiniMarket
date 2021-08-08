package com.example.easymeat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easymeat.R
import com.example.easymeat.fragments.CartListener
import com.example.easymeat.models.Product

class CartAdapter(var list: List<Product>?, var listener: CartListener): RecyclerView.Adapter<CartViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(list!![position], listener)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

}