package com.example.accesodatosalex.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "orders", foreignKeys = [
    ForeignKey(entity = Customer::class, parentColumns = ["customer_id"], childColumns = ["customer_id"])
])
data class Order(
    @PrimaryKey val order_id: Int,
    val customer_id: Int,
    val order_date: String,
    val amount: Float
)
