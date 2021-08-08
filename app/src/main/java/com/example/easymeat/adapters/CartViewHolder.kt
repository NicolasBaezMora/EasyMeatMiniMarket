package com.example.easymeat.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.easymeat.fragments.CartListener
import com.example.easymeat.models.Cart
import com.example.easymeat.models.Product
import kotlinx.android.synthetic.main.item_cart.view.*

class CartViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    fun bind(item: Product, listener: CartListener){
        itemView.textViewTypeMeatCart.text = item.typeMeat
        itemView.textViewTypeCutCart.text = item.typeCut
        itemView.textViewPriceCart.text = item.price.toString()
        itemView.textViewAmount.text = "Cantidad: ${item.cant}"

        itemView.imageButtonAdd.setOnClickListener{
            Cart.addProduct(item, 1)
            listener.reloadCartData()
        }
        itemView.imageButtonRemove.setOnClickListener{
            Cart.removeProduct(item, 1)
            listener.reloadCartData()
        }
    }



}