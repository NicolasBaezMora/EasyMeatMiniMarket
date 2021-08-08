package com.example.easymeat.viewmodels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easymeat.repos.FirebaseRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelAddProduct: ViewModel() {

    private val firebaseRepo = FirebaseRepo()

    private val _progressBar = MutableLiveData<Boolean>()
    val progressBar: LiveData<Boolean> get() = _progressBar

    fun addProduct(typeMeat: String, typeCut: String, price: Double, context: Context, shopProduct: String){
        if (checkInputs(typeMeat, typeCut, price)){
            try{
                viewModelScope.launch {
                    _progressBar.value = true
                    withContext(Dispatchers.IO) {
                        firebaseRepo.addProduct(shopProduct, typeMeat, typeCut, price, 5.0)
                    }
                    _progressBar.value = false
                }
            }catch (e: Exception){
                Toast.makeText(context, "Error: ${e.message} intentalo nuevamente", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(context, "Debes llenar todos los datos correctamente!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkInputs(typeMeat: String, typeCut: String, price: Double): Boolean{
        return !(typeMeat.isEmpty() || typeCut.isEmpty() || price == 0.0)
    }

}