package com.example.accesodatosalex.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.accesodatosalex.entity.Project

@Dao
interface ProjectDao {
    @Query("SELECT * FROM projects")
    suspend fun getAllProjects(): List<Project>

    @Insert
    suspend fun insertProjects(projects: List<Project>)
}
