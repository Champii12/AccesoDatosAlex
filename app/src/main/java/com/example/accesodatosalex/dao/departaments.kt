package com.example.accesodatosalex.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.accesodatosalex.entity.Department

@Dao
interface DepartmentDao {
    @Query("SELECT * FROM departments")
    suspend fun getAllDepartments(): List<Department>

    @Insert
    suspend fun insertDepartments(departments: List<Department>)
}
