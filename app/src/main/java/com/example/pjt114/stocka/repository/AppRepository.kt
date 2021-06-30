package com.example.pjt114.stocka.repository

import com.example.pjt114.stocka.db.AppDatabase
import com.example.pjt114.stocka.model.User

class AppRepository(private val db: AppDatabase) {
    fun getUsers() = db.getUserDao().getUserDetails()
    suspend fun insertNewUser (user: User)= db.getUserDao().insertUserData(user)

}