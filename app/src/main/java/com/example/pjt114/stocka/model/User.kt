package com.example.pjt114.stocka.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var fullName: String,
    var emailAddress: String,
    var phoneNumber: String,
    var industry: String,
    var userName: String,
    var password: String
)
