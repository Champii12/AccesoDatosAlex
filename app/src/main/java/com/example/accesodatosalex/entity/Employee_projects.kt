package com.example.accesodatosalex.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "employee_projects", primaryKeys = ["employee_id", "project_id"], foreignKeys = [
    ForeignKey(entity = Employee::class, parentColumns = ["employee_id"], childColumns = ["employee_id"]),
    ForeignKey(entity = Project::class, parentColumns = ["project_id"], childColumns = ["project_id"])
])
data class EmployeeProject(
    val employee_id: Int,
    val project_id: Int,
    val hours_worked: Float
)
