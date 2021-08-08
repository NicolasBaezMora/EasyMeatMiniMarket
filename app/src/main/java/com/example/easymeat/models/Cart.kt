package com.example.easymeat.models


object Cart {
    var list: MutableMap<Product, Int> = mutableMapOf()

    fun addProduct(item: Product, amount: Int){
        val currentAmount: Int = list[item] ?: 0
        list[item] = currentAmount + amount
    }

    fun removeProduct(item: Product, amount: Int){
        val currentAmount: Int? = list[item]
        if(currentAmount != null){
            list[item] = currentAmount - amount
            if(list[item] == 0) list.remove(item)
        }

    }


}