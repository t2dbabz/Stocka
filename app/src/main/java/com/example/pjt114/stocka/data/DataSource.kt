package com.example.pjt114.stocka.data

import com.example.pjt114.stocka.R
import com.example.pjt114.stocka.model.ProductItem

class DataSource (){

    fun loadProducts(): MutableList<ProductItem>{
        return mutableListOf<ProductItem>(
            ProductItem(
                name = "Indomie noodles",
                sellingPrice = 150.0,
                buyingPrice = 100.0,
                quantity = 500,
                productImage = R.drawable.indomitables_chicken_70g,
                barcode = "089686130010"
            ),

            ProductItem(
                name = "Kelloggs Coco pops",
                sellingPrice = 50.0,
                buyingPrice = 40.0,
                quantity = 250,
                productImage = R.drawable.cocopops,
                barcode = "6154000101282"

            )
        )
    }
}