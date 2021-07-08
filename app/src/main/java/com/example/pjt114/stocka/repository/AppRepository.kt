package com.example.pjt114.stocka.repository

import com.example.pjt114.stocka.db.AppDatabase
import com.example.pjt114.stocka.model.Expense
import com.example.pjt114.stocka.model.ProductItem
import com.example.pjt114.stocka.model.User

class AppRepository(private val db: AppDatabase) {
    fun getUsers() = db.getUserDao().getUserDetails()
    suspend fun insertNewUser (user: User)= db.getUserDao().insertUserData(user)

    fun getExpenses() = db.getExpenseDao().getAllExpense()
    suspend fun insertNewExpense(expense: Expense) = db.getExpenseDao().insertExpense(expense)

    suspend fun deleteExpense(expense: Expense) = db.getExpenseDao().deleteExpense(expense)

    fun getAllProducts() =db.getProductDao().getAllProductDetails()
    suspend fun insertNewProduct(productItem: ProductItem )= db.getProductDao().insertProductData(productItem)

    fun getAllProductQuantity() = db.getProductDao().getAllProductQuantity()
    fun getAllProductQuantitySold() = db.getProductDao().getAllProductQuantitySold()
    fun getTotalSales() = db.getProductDao().getTotalSales()
    fun getProductType(productType: String) = db.getProductDao().getAllProductType(productType)
    fun getAllProductsByMostSold() =db.getProductDao().getAllProductDetailsByMostSold()

    suspend fun deleteProduct(productItem: ProductItem) = db.getProductDao().deleteProduct(productItem)

    fun searchDatabase(searchQuery: String) = db.getProductDao().searchDatabase(searchQuery)

    fun searchDatabaseMostSelling(searchQuery: String) = db.getProductDao().searchDatabaseForMostSelling(searchQuery)

}