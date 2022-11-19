package com.example.marketprices1.models

import java.io.Serializable

data class Product(
    val productCode: Int,
    val name: String,
    val price: Int,
    val priceDate: String,
    val place: String,
    val url: Int
) : Serializable