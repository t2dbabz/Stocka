package com.example.pjt114.stocka.repository

import com.example.pjt114.stocka.db.AppDatabase
import com.example.pjt114.stocka.model.Expense
import com.example.pjt114.stocka.model.User

class AppRepository(private val db: AppDatabase) {
    fun getUsers() = db.getUserDao().getUserDetails()
    suspend fun insertNewUser (user: User)= db.getUserDao().insertUserData(user)

    fun getExpenses() = db.getExpenseDao().getAllExpense()
    suspend fun insertNewExpense(expense: Expense) = db.getExpenseDao().insertExpense(expense)

    suspend fun deleteExpense(expense: Expense) = db.getExpenseDao().deleteExpense(expense)

}