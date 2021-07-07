package com.example.pjt114.stocka.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pjt114.stocka.model.ProductItem


@Dao
interface ProductDao {
    @Query("SELECT * FROM product_inventory")
    fun getAllProductDetails(): LiveData<List<ProductItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductData(productItem: ProductItem)

    @Query("SELECT quantity FROM product_inventory")
    fun getAllProductQuantity(): LiveData<List<Int>>

    @Query("SELECT quantitySold FROM product_inventory")
    fun getAllProductQuantitySold(): LiveData<List<Int>>

    @Query("SELECT * FROM product_inventory")
    fun getTotalSales(): LiveData<List<ProductItem>>
}