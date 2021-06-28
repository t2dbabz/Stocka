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

            ),

            ProductItem(
                name = "Peak Milk Sachet 20g",
                sellingPrice = 60.0,
                buyingPrice = 55.0,
                quantity = 200,
                productImage = R.drawable.fullcream_sachet_20g,
                barcode = "8716200449304"

            ),

            ProductItem(
                name = "Milo Sachet 20g",
                sellingPrice = 50.0,
                buyingPrice = 40.0,
                quantity = 300,
                productImage = R.drawable.milo_sachet_20g,
                barcode = "6151100032379"

            ),
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

            ),

            ProductItem(
                name = "Peak Milk Sachet 20g",
                sellingPrice = 60.0,
                buyingPrice = 55.0,
                quantity = 200,
                productImage = R.drawable.fullcream_sachet_20g,
                barcode = "8716200449304"

            ),

            ProductItem(
                name = "Milo Sachet 20g",
                sellingPrice = 50.0,
                buyingPrice = 40.0,
                quantity = 300,
                productImage = R.drawable.milo_sachet_20g,
                barcode = "6151100032379"

            ),





        )
    }
}