package com.example.accesodatosalex.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "projects")
data class Project(
    @PrimaryKey val project_id: Int,
    val project_name: String,
    val department_id: Int,
    val budget: Float,
    val start_date: String,
    val end_date: String
)
