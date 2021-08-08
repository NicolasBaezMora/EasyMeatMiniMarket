package com.example.easymeat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.easymeat.R
import com.example.easymeat.viewmodels.ViewModelAddProduct
import kotlinx.android.synthetic.main.fragment_add_product.*

class AddProductFragment : Fragment(), View.OnClickListener {

    private lateinit var vm: ViewModelAddProduct

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProviders.of(this).get(ViewModelAddProduct::class.java)

        vm.progressBar.observe(viewLifecycleOwner, Observer {
            progressBarAddProduct.visibility = if(it) View.VISIBLE else View.GONE
        })

        btnAddProduct.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnAddProduct -> {
                completeAddProduct(arguments?.getString("shopProduct")!!)
            }
        }
    }

    private fun completeAddProduct(shopProduct: String){
        val typeMeat = editTextTypeMeat.text.toString()
        val typeCut = editTextTypeCut.text.toString()
        val price = if (editTextPrice.text.isNullOrEmpty()) 0.0 else editTextPrice.text.toString().toDouble()

        vm.addProduct(typeMeat, typeCut, price, requireContext(), shopProduct)
    }



}