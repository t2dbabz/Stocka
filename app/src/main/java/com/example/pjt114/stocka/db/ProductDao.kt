package com.example.pjt114.stocka.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pjt114.stocka.model.ProductItem
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {
    @Query("SELECT * FROM product_inventory")
    fun getAllProductDetails(): LiveData<List<ProductItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductData(productItem: ProductItem)

    @Delete
    suspend fun deleteProduct(productItem: ProductItem)

    @Query("SELECT quantity FROM product_inventory")
    fun getAllProductQuantity(): LiveData<List<Int>>

    @Query("SELECT quantitySold FROM product_inventory")
    fun getAllProductQuantitySold(): LiveData<List<Int>>

    @Query("SELECT * FROM product_inventory")
    fun getTotalSales(): LiveData<List<ProductItem>>

    // get all product list by type type
    @Query("SELECT * FROM product_inventory WHERE productType == :productType ORDER by createdAt DESC")
    fun getAllProductType(productType: String): LiveData<List<ProductItem>>

    @Query("SELECT * FROM product_inventory ORDER by quantitySold DESC")
    fun getAllProductDetailsByMostSold(): LiveData<List<ProductItem>>

    @Query("SELECT * FROM product_inventory WHERE name LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): LiveData<List<ProductItem>>

    @Query("SELECT * FROM product_inventory WHERE name LIKE :searchQuery ORDER by quantitySold DESC")
    fun searchDatabaseForMostSelling(searchQuery: String): LiveData<List<ProductItem>>
}