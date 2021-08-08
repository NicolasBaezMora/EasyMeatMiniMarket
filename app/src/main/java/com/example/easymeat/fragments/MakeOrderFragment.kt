package com.example.easymeat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.easymeat.R
import com.example.easymeat.models.Cart
import com.example.easymeat.models.Product
import com.example.easymeat.viewmodels.ViewModelMakeOrder
import kotlinx.android.synthetic.main.fragment_make_order.*


class MakeOrderFragment : Fragment(), View.OnClickListener {

    private val vm by lazy{ ViewModelProviders.of(this).get(ViewModelMakeOrder::class.java) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_make_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.progressBar.observe(viewLifecycleOwner, Observer {
            progressBarMakeOrder.visibility = if (it) View.VISIBLE else View.GONE
        })

        btnMakeOrder.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnMakeOrder -> competeMakeOrder()
        }
    }

    private fun competeMakeOrder(){
        val name = editTextName.text.toString()
        val address = editTextAddress.text.toString()
        val phone = if (editTextPhone.text.isNullOrEmpty()) 0 else editTextPhone.text.toString().toLong()
        var description = ""
        var total = 0.0

        val productList = mutableListOf<Product>()
        for(item in Cart.list){
            val product = item.key
            product.cant = item.value
            productList.add(product)
            description += "${item.key.typeMeat} -> ${item.key.typeCut}. "
        }

        if(productList.isNotEmpty()){
            total = productList.map{ it.price * it.cant }.reduce{ acc, item -> item + acc }
            vm.makeOrder(address, description, name, false, phone, total, requireContext())
        }

    }

}