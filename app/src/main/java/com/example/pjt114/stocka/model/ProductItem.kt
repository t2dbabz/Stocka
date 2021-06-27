package com.example.pjt114.stocka.model

import java.io.Serializable

data class ProductItem(
    var name: String,
    var sellingPrice: Double,
    var buyingPrice: Double,
    var quantity: Int,
    val productImage: Int,
    val barcode: String,

) : Serializable
