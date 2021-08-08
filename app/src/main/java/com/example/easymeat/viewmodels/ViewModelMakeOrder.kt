package com.example.easymeat.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easymeat.models.Cart
import com.example.easymeat.repos.FirebaseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger

class ViewModelMakeOrder: ViewModel() {

    private val firebaseRepo = FirebaseRepo()

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> get() = _progressBar

    fun makeOrder(address: String, description: String, name: String, orderConfirmed: Boolean, phone: Long, total: Double, context: Context){
        if (checkInputs(name, address, phone)){
            try{
                viewModelScope.launch {
                    _progressBar.value = true
                    withContext(Dispatchers.IO) {
                        firebaseRepo.makeOrder(address, description, name, orderConfirmed, phone, total)
                    }
                    _progressBar.value = false
                }
                Cart.list = mutableMapOf()
                Toast.makeText(context, "Se realizo la orden!", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                Toast.makeText(context, "Error: ${e.message} intentalo nuevamente", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Debes llenar todos los datos correctamente!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkInputs(name: String, address: String, phone: Long): Boolean{
        return !(name.isEmpty() || address.isEmpty() || phone.equals(0))
    }


}