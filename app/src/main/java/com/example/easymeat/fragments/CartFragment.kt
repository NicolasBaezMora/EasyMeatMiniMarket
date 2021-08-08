package com.example.easymeat.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easymeat.R
import com.example.easymeat.adapters.CartAdapter
import com.example.easymeat.models.Cart
import com.example.easymeat.models.Product
import kotlinx.android.synthetic.main.fragment_car.*


class CartFragment : Fragment(), CartListener, View.OnClickListener {

    private var adapter: CartAdapter? = null
    private val navigator by lazy{ findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CartAdapter(listOf(), this)
        recyclerViewProductListCar.adapter = adapter
        recyclerViewProductListCar.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        floatingButtonMakeOrder.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        reloadCartData()

    }

    @SuppressLint("SetTextI18n")
    override fun reloadCartData(){
        val productList = mutableListOf<Product>()
        for(item in Cart.list){
            val product = item.key
            product.cant = item.value
            productList.add(product)
        }

        if(productList.isNotEmpty()){
            textViewTotal.text = "Cantidad total: $${productList.map{ it.price * it.cant }.reduce{ acc, item -> item + acc } ?: 0}"
        }else if(productList.isEmpty()){
            textViewTotal?.text = "Total: $0"
        }

        adapter?.list = productList
        adapter?.notifyDataSetChanged()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.floatingButtonMakeOrder -> {
                if(Cart.list.isEmpty()){
                    Toast.makeText(context, "El carrito esta vacio", Toast.LENGTH_SHORT).show()
                }else{
                    navigator.navigate(R.id.action_cartFragment_to_makeOrderFragment)
                }
            }
        }
    }
}