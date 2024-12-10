package com.example.accesodatosalex.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.accesodatosalex.entity.EmployeeProject

@Dao
interface EmployeeProjectDao {
    @Query("SELECT * FROM employee_projects")
    suspend fun getAllEmployeeProjects(): List<EmployeeProject>

    @Insert
    suspend fun insertEmployeeProjects(employeeProjects: List<EmployeeProject>)
}
