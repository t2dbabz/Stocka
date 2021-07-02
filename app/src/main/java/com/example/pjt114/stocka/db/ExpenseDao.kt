package com.example.pjt114.stocka.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pjt114.stocka.model.Expense


@Dao
interface ExpenseDao {

    @Query("SELECT * FROM all_expense")
    fun getAllExpense(): LiveData<List<Expense>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)


}