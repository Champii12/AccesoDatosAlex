package com.example.accesodatosalex.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.accesodatosalex.entity.OrderItem

@Dao
interface OrderItemDao {
    @Query("SELECT * FROM order_items")
    suspend fun getAllOrderItems(): List<OrderItem>

    @Insert
    suspend fun insertOrderItems(orderItems: List<OrderItem>)
}
