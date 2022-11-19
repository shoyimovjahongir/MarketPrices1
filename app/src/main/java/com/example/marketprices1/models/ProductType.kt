package com.example.marketprices1.models

import java.io.Serializable

data class ProductType(
    val type: String,
    val url: Int
) : Serializable