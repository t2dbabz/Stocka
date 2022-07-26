package com.example.pjt114.stocka.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var fullName: String,
    var emailAddress: String,
    var phoneNumber: String,
    var industry: String,
    var userName: String,
    var password: String
): Serializable
