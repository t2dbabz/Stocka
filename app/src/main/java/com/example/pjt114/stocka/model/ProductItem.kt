package com.example.pjt114.stocka.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.text.DateFormat

@Entity(tableName = "product_inventory")
data class ProductItem(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "product_image")
    var productImage: String? = null,
    @ColumnInfo(name = "sellingPrice")
    var sellingPrice: Double,
    @ColumnInfo(name = "buyingPrice")
    var buyingPrice: Double,
    @ColumnInfo(name = "quantity")
    var quantity: Int = 0,
    @ColumnInfo(name = "barcode")
    var barcode: String? = null,
    @ColumnInfo(name = "quantitySold")
    var quantitySold: Int = 0,
    @ColumnInfo(name = "productType")
    var productType: String? = null,
    @ColumnInfo(name = "createdAt")
    var createdAt: Long =
        System.currentTimeMillis()

) : Serializable{

    val createdAtDateFormat: String
        get() = DateFormat.getDateTimeInstance()
            .format(createdAt) // Date Format: Jan 11, 2021, 11:30 AM
}
