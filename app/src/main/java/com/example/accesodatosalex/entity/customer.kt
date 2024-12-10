package com.example.accesodatosalex.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "customers")
data class Customer(
    @PrimaryKey val customer_id: Int, // La clave primaria
    val customer_name: String,
    val contact_email: String,
    val contact_phone: String
)
