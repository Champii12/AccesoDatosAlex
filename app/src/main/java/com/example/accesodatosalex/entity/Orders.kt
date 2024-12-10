package com.example.accesodatosalex.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey val order_id: Int,
    val customer_id: Int,
    val order_date: String,
    val amount: Float
)
