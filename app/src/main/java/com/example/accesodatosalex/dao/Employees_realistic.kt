package com.example.accesodatosalex.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.accesodatosalex.entity.Employee

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employees")
    suspend fun getAllEmployees(): List<Employee>

    @Insert
    suspend fun insertEmployees(employees: List<Employee>)
}
