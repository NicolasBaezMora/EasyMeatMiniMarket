package com.example.easymeat.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.easymeat.R
import com.example.easymeat.adapters.OrderAdapter
import com.example.easymeat.models.Order
import com.example.easymeat.viewmodels.ViewModelOrder
import kotlinx.android.synthetic.main.fragment_order_list.*


class OrderListFragment : Fragment(), OrderAdapter.OnOrderClickListener {

    private val vm by lazy { ViewModelProviders.of(this).get(ViewModelOrder::class.java) }
    private val orderAdapter by lazy { OrderAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewOderList.adapter = orderAdapter
        recyclerViewOderList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        observeOrderData()

    }

    private fun observeOrderData(){
        Log.e("BUCLEANDO", "F")
        vm.fetchOrderData().observe(viewLifecycleOwner, Observer {
            orderAdapter.listOrder = it
            orderAdapter.notifyDataSetChanged()
            observeOrderData()
        })
    }

    override fun onItemClick(item: Order) {
        vm.updateConfirmOder(item.id)
    }


}