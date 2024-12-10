package com.example.accesodatosalex.entity

import androidx.room.Entity

@Entity(tableName = "employee_projects", primaryKeys = ["employee_id", "project_id"])
data class EmployeeProject(
    val employee_id: Int,
    val project_id: Int,
    val hours_worked: Float
)
