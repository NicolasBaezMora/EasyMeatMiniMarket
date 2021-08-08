package com.example.easymeat.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.easymeat.models.Order
import com.example.easymeat.models.Product
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseRepo {

    val DB = FirebaseFirestore.getInstance()

    fun addProduct(shopProduct: String, typeMeat: String, typeCut: String, price: Double, qualify: Double){
        DB.collection(shopProduct).add(
            mapOf(
                "TYPEMEAT" to typeMeat,
                "TYPECUT" to typeCut,
                "PRICE" to price,
                "QUALIFY" to qualify
            )
        )
    }

    fun editProduct(shopProduct: String, idProduct: String, typeMeat: String, typeCut: String, price: Double){
        DB.collection(shopProduct).document(idProduct).update(
            mapOf(
                "TYPEMEAT" to typeMeat,
                "TYPECUT" to typeCut,
                "PRICE" to price
            )
        )
    }

    fun makeOrder(address: String, description: String, name: String, orderConfirmed: Boolean, phone: Long, total: Double){
        DB.collection("ORDERS").add(
            mapOf(
                "NAME" to name,
                "ADDRESS" to address,
                "PHONE" to phone,
                "DESCRIPTION" to description,
                "TOTAL" to total,
                "ORDERCONFIRMED" to orderConfirmed
            )
        )
    }

    fun updateQualifyShop(idShop: String, newQualify: Int){
        DB.collection("SHOPS").document(idShop).update("QUALIFY", newQualify)
    }

    fun updateQualifyProduct(shopProduct: String, idProduct: String, newQualify: Double){
        DB.collection(shopProduct).document(idProduct).update( mapOf("QUALIFY" to newQualify) )
    }

    fun updateConfirmOrder(idOrder: String){
        DB.collection("ORDERS").document(idOrder).update( mapOf( "ORDERCONFIRMED" to true ) )
    }

    fun getProductData(shopProduct: String): LiveData<MutableList<Product>>{
        val mutableData = MutableLiveData<MutableList<Product>>()
        DB.collection(shopProduct).get().addOnSuccessListener { resultQuery ->
            val listData = mutableListOf<Product>()
            resultQuery.forEach { document ->
                listData.add(
                        Product(
                                document.id,
                                document.getString("TYPEMEAT")!!,
                                document.getString("TYPECUT")!!,
                                document.getDouble("PRICE")!!,
                                document.getDouble("QUALIFY")!!
                        )
                )
            }
            mutableData.value = listData
        }
        return mutableData
    }

    fun getOrderData(): LiveData<MutableList<Order>> {
        val mutableData = MutableLiveData<MutableList<Order>>()
        DB.collection("ORDERS").get().addOnSuccessListener { resultQuery ->
            val listData = mutableListOf<Order>()
            resultQuery.forEach { document ->
                if (!(document.getBoolean("ORDERCONFIRMED") as Boolean)){
                    listData.add(
                        Order(
                            document.id,
                            document.getString("NAME")!!,
                            document.getString("ADDRESS")!!,
                            document.getLong("PHONE")!!,
                            document.getString("DESCRIPTION")!!,
                            document.getDouble("TOTAL")!!,
                            document.getBoolean("ORDERCONFIRMED")!!
                        )
                    )
                }
            }
            mutableData.value = listData
        }
        return mutableData
    }
}