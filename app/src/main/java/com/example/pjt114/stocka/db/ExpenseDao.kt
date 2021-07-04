package com.example.pjt114.stocka.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.*
import com.example.pjt114.stocka.model.Expense
import kotlinx.coroutines.launch


@Dao
interface ExpenseDao {

    @Query("SELECT * FROM all_expense")
    fun getAllExpense(): LiveData<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)

    // used to delete transaction
    @Delete
    suspend fun deleteExpense(expense: Expense)

}