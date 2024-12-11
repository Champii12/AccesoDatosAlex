package com.example.accesodatosalex

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.accesodatosalex.entity.Customer
import com.example.accesodatosalex.entity.Department
import com.example.accesodatosalex.entity.Employee
import com.example.accesodatosalex.entity.EmployeeProject
import com.example.accesodatosalex.entity.Order
import com.example.accesodatosalex.entity.OrderItem
import com.example.accesodatosalex.entity.Project
import com.example.accesodatosalex.ui.theme.AccesoDatosAlexTheme
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "company_database"
        ).allowMainThreadQueries().build()

        setContent {
            AccesoDatosAlexTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") { MainScreen(innerPadding, navController, database) }
                    }
                }
            }
        }
    }


}

@Composable
fun MainScreen(innerPadding: PaddingValues, navController: NavController, db: AppDatabase) {
    val data = runBlocking { db.projectDao().getProyectQuery() }
    val columnas = 4
    Column(modifier = Modifier.padding(innerPadding)) {
        Text(
            "ACCESO A DATOS",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .border(1.dp, Color.Gray)
                .padding(16.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color.Red
        )
        LazyVerticalGrid(columns = GridCells.Fixed(columnas)
        ) {
            listOf("Project Id","Project Salary Costs","Budget","Cost Fraction").forEach {
                item{
                    Text(
                        it,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(90.dp)
                            .background(Color.Black)
                            .padding(8.dp),
                        color = Color.Red
                    )
                }
            }

            data.forEach { value ->
                listOf(
                    value.projectId.toString(),
                    value.projectSalaryCosts.toString(),
                    value.budget.toString(),
                    value.costFraction.toString()).forEach{ item ->
                    item {
                        Text(
                            item,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(90.dp)
                                .background(Color.Gray)
                                .padding(8.dp),
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

