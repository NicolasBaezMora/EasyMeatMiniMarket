package com.example.easymeat.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easymeat.R
import com.example.easymeat.models.Order
import kotlinx.android.synthetic.main.item_order.view.*

class OrderAdapter(val itemClickListener: OnOrderClickListener): RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    var listOrder: List<Order>? = listOf()

    inner class OrderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(item: Order){
            itemView.textViewName.text = item.NAME
            itemView.textViewAddress.text = item.ADDRESS
            itemView.textViewPhone.text = item.PHONE.toString()
            itemView.textViewDescription.text = item.DESCRIPTION
            itemView.textViewTotal.text = "Total a pagar: $${item.TOTAL}"

            itemView.btnOrderConfirmed.setOnClickListener {
                itemClickListener.onItemClick(item)
            }
        }
    }

    interface OnOrderClickListener{
        fun onItemClick(item: Order)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order, parent, false)
        return OrderViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        holder.bind(listOrder!![position])
    }

    override fun getItemCount(): Int {
        return listOrder!!.size
    }

}