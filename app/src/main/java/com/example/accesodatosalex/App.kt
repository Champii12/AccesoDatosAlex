package com.example.accesodatosalex

import android.app.Application
import androidx.room.Room
import com.example.accesodatosalex.ui.theme.AppDatabase

class App : Application() {

    // Variable para la instancia de la base de datos
    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()

        // Inicializar la base de datos
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "company_database" // Nombre de la base de datos
        ).build()
    }
}
