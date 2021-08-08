package com.example.easymeat.models

data class Order(
        val id: String,
        val NAME: String,
        val ADDRESS: String,
        val PHONE: Long,
        val DESCRIPTION: String,
        val TOTAL: Double,
        val ORDERCONFIRMED: Boolean
)