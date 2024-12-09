package com.example.accesodatosalex.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity

class DataListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tableName = intent.getStringExtra("tableName")
    }
}