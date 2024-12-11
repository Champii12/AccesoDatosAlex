package com.example.accesodatosalex.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "order_items", foreignKeys = [
    ForeignKey(entity = Order::class, parentColumns = ["order_id"], childColumns = ["order_id"])
])
data class OrderItem(
    @PrimaryKey val order_item_id: Int,
    val order_id: Int,
    val product_name: String,
    val quantity: Int,
    val price: Float
)
