package com.example.accesodatosalex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.accesodatosalex.ui.theme.AccesoDatosAlexTheme
import com.example.accesodatosalex.ui.theme.AppDatabase
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AccesoDatosAlexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        val listaNombreTabla = listOf("customers", "departments", "employee_projects", "employees", "order_items", "orders", "projects")

                        Text("ACCESO A DATOS",
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color.Gray)
                                .border(1.dp, Color.Gray)
                                .padding(16.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp,
                            color = Color.Red
                        )

                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
                        ) {
                            listaNombreTabla.forEach { nombre ->
                                Button(
                                    onClick = { mostrarDatosDeTabla(nombre) },
                                    modifier = Modifier
                                        .size(200.dp, 80.dp)
                                        .padding(8.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Black,
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(nombre)
                                }
                            }
                        }

                        // Aquí podrás añadir la lógica para mostrar los datos de cada tabla
                        // por ejemplo, una lista de customers
                    }
                }
            }
        }
    }

    // Función que muestra los datos al hacer clic en el botón
    private fun mostrarDatosDeTabla(tabla: String) {
        lifecycleScope.launch {
            // Acceder a los datos desde la base de datos
            when (tabla) {
                "customers" -> {
                    val customers = getDatabase().customerDao().getAllCustomers()
                    mostrarDatosEnVista(customers)
                }
                "departments" -> {
                    val departments = getDatabase().departmentDao().getAllDepartments()
                    mostrarDatosEnVista(departments)
                }
                "employee_projects" -> {
                    val employeeProjects = getDatabase().employeeProjectDao().getAllEmployeeProjects()
                    mostrarDatosEnVista(employeeProjects)
                }
                "employees" -> {
                    val employees = getDatabase().employeeDao().getAllEmployees()
                    mostrarDatosEnVista(employees)
                }

            }
        }
    }

    private fun mostrarDatosEnVista(data: List<Any>) {
        // Aquí puedes manejar la lógica para mostrar los datos en tu vista
        // Por ejemplo, mostrar un listado en un nuevo Composable
    }

    private fun getDatabase(): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app-database"
        ).build()
    }
}
