package com.example.accesodatosalex.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.accesodatosalex.entity.Project
import com.example.accesodatosalex.query.ProyectQuery

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects")
    suspend fun getAllProjects(): List<Project>

    @Insert
    suspend fun insertProjects(projects: List<Project>)

    @Query(
        """
        SELECT projects.project_id AS projectId, 
        SUM(hours_worked * salary) AS projectSalaryCosts, 
        budget, 
        SUM(hours_worked * salary) / budget AS costFraction
        FROM projects
        JOIN employee_projects ON projects.project_id = employee_projects.project_id
        JOIN employees ON employee_projects.employee_id = employees.employee_id
        GROUP BY projects.project_id
        """
    )
    @Transaction
    suspend fun getProyectQuery(): List<ProyectQuery>
}
