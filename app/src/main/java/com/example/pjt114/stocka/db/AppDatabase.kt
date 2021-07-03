package com.example.pjt114.stocka.db

import android.content.Context
import androidx.room.*
import com.example.pjt114.stocka.model.Expense
import com.example.pjt114.stocka.model.User

@Database(
    version = 2,
    entities = [User::class, Expense::class],
   exportSchema = true
)
abstract class AppDatabase:RoomDatabase() {

    abstract fun getUserDao(): UserDao
    abstract fun getExpenseDao(): ExpenseDao

    companion object{
        @Volatile
        private var instance: AppDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database.db"
            )
                .fallbackToDestructiveMigration()
                .build()

    }
}