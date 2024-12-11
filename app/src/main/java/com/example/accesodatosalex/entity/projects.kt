package com.example.accesodatosalex.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "projects", foreignKeys = [
    ForeignKey(entity = Department::class, parentColumns = ["department_id"], childColumns = ["department_id"])
])
data class Project(
    @PrimaryKey val project_id: Int,
    val project_name: String,
    val department_id: Int,
    val budget: Float,
    val start_date: String,
    val end_date: String
)
