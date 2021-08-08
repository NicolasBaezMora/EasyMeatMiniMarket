package com.example.easymeat.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.easymeat.models.Cart
import com.example.easymeat.models.Product
import kotlinx.android.synthetic.main.item_product.view.*
import kotlinx.android.synthetic.main.item_product.view.textViewPrice
import kotlinx.android.synthetic.main.item_product.view.textViewTypeCut
import kotlinx.android.synthetic.main.item_product.view.textViewTypeMeat
import kotlinx.android.synthetic.main.item_product.view.textViewQualify
import kotlinx.android.synthetic.main.item_product_edit.view.*
import kotlinx.android.synthetic.main.item_product_qualify.view.*

class ProductAdapter(
        private val viewLayout: Int,
        private val typeButton: String,
        private val itemClickListenerAdd: OnProductClickListener,
        private val itemClickListenerQualify: OnProductClickListener,
        private val context: Context
        ): RecyclerView.Adapter<BaseProductViewHolder<Product>>() {

    var listProduct: List<Product>? = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewLayout, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseProductViewHolder<Product>, position: Int) {
        holder.bind(listProduct!![position], position, typeButton, context)
    }

    override fun getItemCount(): Int {
        Log.i("SIZE:", listProduct!!.size.toString())
        return listProduct!!.size
    }

    interface OnProductClickListener{
        fun onItemClickEdit(item: Product)
        fun onItemClickQualify(item: Product)
    }

    inner class ProductViewHolder(itemView: View): BaseProductViewHolder<Product>(itemView){


        override fun bind(item: Product, position: Int, typeButton: String, context: Context){
            itemView.textViewTypeMeat.text = item.typeMeat
            itemView.textViewTypeCut.text = item.typeCut
            itemView.textViewPrice.text = item.price.toString()
            itemView.textViewQualify.text = item.qualify.toString()

            when(typeButton){
                "ADD" -> loadAddButton(item, context)
                "EDIT" -> loadEditButton(item)
                "QUALIFY" -> loadQualifyButton(item)
            }


        }

        private fun loadAddButton(item: Product, context: Context){
            itemView.buttonAdd.setOnClickListener{
                Cart.addProduct(item, 1)//Objeto singleton
                Log.e("ITEMMMM", "${item.price}")
                Toast.makeText(context, "Producto agregado satisfactoriamente", Toast.LENGTH_SHORT).show()
            }
        }

        private fun loadEditButton(item: Product){
            itemView.buttonEdit.setOnClickListener{
                itemClickListenerAdd.onItemClickEdit(item)
            }
        }

        private fun loadQualifyButton(item: Product){
            itemView.buttonQualify.setOnClickListener {
                itemClickListenerQualify.onItemClickQualify(item)
            }
        }



    }
}