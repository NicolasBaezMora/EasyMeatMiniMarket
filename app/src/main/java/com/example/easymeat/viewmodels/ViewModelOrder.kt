package com.example.easymeat.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easymeat.models.Order
import com.example.easymeat.repos.FirebaseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelOrder: ViewModel() {

    private val firebaseRepo = FirebaseRepo()

    fun fetchOrderData(): LiveData<MutableList<Order>> {
        val mutableData = MutableLiveData<MutableList<Order>>()
        viewModelScope.launch {

            val queryResult = withContext(Dispatchers.IO){
                firebaseRepo.getOrderData()
            }
            queryResult.observeForever{ orderList ->
                mutableData.value = orderList
            }

        }
        return mutableData
    }

    fun updateConfirmOder(idOrder: String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                firebaseRepo.updateConfirmOrder(idOrder)
            }
        }
    }

}