package com.example.accesodatosalex.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "departments")
data class Department(
    @PrimaryKey val department_id: Int,
    val department_name: String,
    val manager_id: Int
)
