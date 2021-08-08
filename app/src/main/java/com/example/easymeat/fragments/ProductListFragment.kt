package com.example.easymeat.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easymeat.R
import com.example.easymeat.adapters.ProductAdapter
import com.example.easymeat.models.Product
import com.example.easymeat.viewmodels.ViewModelProduct
import kotlinx.android.synthetic.main.fragment_product_list.*

class ProductListFragment : Fragment(), ProductAdapter.OnProductClickListener{

    private lateinit var vm: ViewModelProduct
    private lateinit var productAdapter: ProductAdapter
    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm = ViewModelProviders.of(this).get(ViewModelProduct::class.java)
        navigator = findNavController()

        val typeButton = arguments?.getString("typeButton")!!

        productAdapter = ProductAdapter(chooseViewLayout(typeButton), typeButton, this, this, requireContext())
        recyclerViewProductList.adapter = productAdapter
        recyclerViewProductList.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        observeProductData(arguments?.getString("shopProduct")!!)


    }

    private fun observeProductData(shopProduct: String){
        vm.fetchProductData(shopProduct).observe(viewLifecycleOwner, Observer {
            productAdapter.listProduct = it
            productAdapter.notifyDataSetChanged()
        })
    }

    private fun chooseViewLayout(typeButton: String): Int{
        return when(typeButton){
            "ADD" -> R.layout.item_product
            "EDIT" -> R.layout.item_product_edit
            "QUALIFY" -> R.layout.item_product_qualify
            else -> 0
        }
    }

    override fun onItemClickEdit(item: Product) {
        navigator.navigate(
                R.id.action_productListFragment_to_editProductFragment,
                bundleOf(
                        "productItem" to item,
                        "shopProduct" to arguments?.getString("shopProduct")!!
                )
        )
    }

    override fun onItemClickQualify(item: Product) {
        navigator.navigate(
                R.id.action_productListFragment_to_qualifyFragment,
                bundleOf(
                        "productItem" to item,
                        "shopProduct" to arguments?.getString("shopProduct")!!
                )
        )
    }


}