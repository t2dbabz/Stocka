package com.example.pjt114.stocka.model

data class Expense(
    var id: Int,
    var name: String,
    var amount: Double,
    var tag: String,
    var date: String,
    var note: String
)
