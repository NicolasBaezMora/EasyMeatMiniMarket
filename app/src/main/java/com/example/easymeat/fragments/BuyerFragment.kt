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
import kotlinx.android.synthetic.main.fragment_buyer.*

class BuyerFragment : Fragment(), View.OnClickListener {

    private lateinit var navigator: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buyer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigator = findNavController()

        btnShops.setOnClickListener(this)
        btnCart.setOnClickListener(this)
        btnQualify.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        val builder = AlertDialog.Builder(requireContext())
        val listShops = listOf("Doncarnes", "Carnecitas")
        when(v?.id){
            R.id.btnShops -> {

                builder.setTitle("Elige una tienda!")
                builder.setItems(listShops.toTypedArray()){ _, item ->
                    when(item){
                        0 -> navigator.navigate(R.id.action_buyerFragment_to_productListFragment, bundleOf("shopProduct" to "DONCARNES", "typeButton" to "ADD"))
                        1 -> navigator.navigate(R.id.action_buyerFragment_to_productListFragment, bundleOf("shopProduct" to "CARNECITAS", "typeButton" to "ADD"))
                    }
                }
                builder.create()
                builder.show()
            }
            R.id.btnCart -> navigator.navigate(R.id.action_buyerFragment_to_cartFragment)
            R.id.btnQualify -> {
                builder.setTitle("Elige una tienda!")
                builder.setItems(listShops.toTypedArray()){ _, item ->
                    when(item){
                        0 -> navigator.navigate(R.id.action_buyerFragment_to_productListFragment, bundleOf("shopProduct" to "DONCARNES", "typeButton" to "QUALIFY"))
                        1 -> navigator.navigate(R.id.action_buyerFragment_to_productListFragment, bundleOf("shopProduct" to "CARNECITAS", "typeButton" to "QUALIFY"))
                    }
                }
                builder.create()
                builder.show()
            }
        }
    }
}