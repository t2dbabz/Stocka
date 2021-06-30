package com.example.pjt114.stocka.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pjt114.stocka.model.User


@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUserDetails(): LiveData<List<User>>

    @Insert
    suspend fun insertUserData(user: User)
}