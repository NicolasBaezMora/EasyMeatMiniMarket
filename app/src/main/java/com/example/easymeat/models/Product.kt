package com.example.easymeat.models

import java.io.Serializable

data class Product(
    val id: String,
    val typeMeat: String,
    val typeCut: String,
    val price: Double,
    val qualify: Double
): Serializable{
    var cant: Int = 1
}
