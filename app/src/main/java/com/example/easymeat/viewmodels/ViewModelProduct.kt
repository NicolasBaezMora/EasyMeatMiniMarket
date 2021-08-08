package com.example.easymeat.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easymeat.models.Product
import com.example.easymeat.repos.FirebaseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelProduct: ViewModel() {

    private val firebaseRepo = FirebaseRepo()


    fun fetchProductData(shopProduct: String): LiveData<MutableList<Product>>{
        val mutableData = MutableLiveData<MutableList<Product>>()
        viewModelScope.launch{
            val queryResult = withContext(Dispatchers.IO){
                firebaseRepo.getProductData(shopProduct)
            }
            queryResult.observeForever { productList ->
                mutableData.value = productList
            }
        }
        return mutableData
    }



}