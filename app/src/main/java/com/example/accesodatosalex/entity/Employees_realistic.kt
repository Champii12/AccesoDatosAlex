package com.example.accesodatosalex.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "employees", foreignKeys = [
    ForeignKey(entity = Department::class, parentColumns = ["department_id"], childColumns = ["department_id"])
])
data class Employee(
    @PrimaryKey val employee_id: Int,
    val first_name: String,
    val last_name: String,
    val department_id: Int,
    val hire_date: String,
    val salary: Float,
    val position: String
)
