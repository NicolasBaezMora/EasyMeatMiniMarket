package com.example.easymeat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.easymeat.R
import com.example.easymeat.models.Product
import com.example.easymeat.viewmodels.ViewModelEditProduct
import kotlinx.android.synthetic.main.fragment_edit_product.*


class EditProductFragment : Fragment(), View.OnClickListener {

    private lateinit var vm: ViewModelEditProduct
    private lateinit var itemProduct: Product

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProviders.of(this).get(ViewModelEditProduct::class.java)

        vm.progressBar.observe(viewLifecycleOwner, Observer {
            progressBarEditProduct.visibility = if (it) View.VISIBLE else View.GONE
        })

        itemProduct = arguments?.getSerializable("productItem") as Product
        editTextTypeMeat.setText(itemProduct?.typeMeat)
        editTextTypeCut.setText(itemProduct?.typeCut)
        editTextPrice.setText(itemProduct?.price.toString())

        btnEditProduct_edit.setOnClickListener(this)
    }

    private fun completeEditProduct(shopProduct: String){
        val typeMeat = editTextTypeMeat.text.toString()
        val typeCut = editTextTypeCut.text.toString()
        val price = if (editTextPrice.text.isNullOrEmpty()) 0.0 else editTextPrice.text.toString().toDouble()

        vm.editProduct(shopProduct, itemProduct.id, typeMeat, typeCut, price, requireContext())
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnEditProduct_edit -> completeEditProduct(arguments?.getString("shopProduct")!!)
        }
    }

}