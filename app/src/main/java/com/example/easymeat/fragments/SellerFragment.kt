package com.example.easymeat.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.easymeat.R
import kotlinx.android.synthetic.main.fragment_seller.*


class SellerFragment : Fragment(), View.OnClickListener {

    private val navigator by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seller, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnAddProduct.setOnClickListener(this)
        btnEditProduct.setOnClickListener(this)
        btnConfirmOrder.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnAddProduct -> {
                val builder = AlertDialog.Builder(requireContext())
                val listShops = listOf("Doncarnes", "Carnecitas")

                builder.setTitle("En cual tienda deseas agregar los productos?")
                builder.setItems(listShops.toTypedArray()){ _, item ->
                    when(item){
                        0 -> navigator.navigate(R.id.action_sellerFragment_to_addProductFragment, bundleOf("shopProduct" to "DONCARNES"))
                        1 -> navigator.navigate(R.id.action_sellerFragment_to_addProductFragment, bundleOf("shopProduct" to "CARNECITAS"))
                    }
                }
                builder.create()
                builder.show()
            }
            R.id.btnEditProduct -> {
                val builder = AlertDialog.Builder(requireContext())
                val listShops = listOf("Doncarnes", "Carnecitas")

                builder.setTitle("En cual tienda deseas editar los productos?")
                builder.setItems(listShops.toTypedArray()){ _, item ->
                    when(item){
                        0 -> navigator.navigate(R.id.action_sellerFragment_to_productListFragment, bundleOf("shopProduct" to "DONCARNES", "typeButton" to "EDIT"))
                        1 -> navigator.navigate(R.id.action_sellerFragment_to_productListFragment, bundleOf("shopProduct" to "CARNECITAS", "typeButton" to "EDIT"))
                    }
                }
                builder.create()
                builder.show()
            }
            R.id.btnConfirmOrder -> navigator.navigate(R.id.action_sellerFragment_to_orderListFragment)
        }
    }

}