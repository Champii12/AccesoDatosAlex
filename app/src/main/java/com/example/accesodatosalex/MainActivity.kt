package com.example.accesodatosalex

import android.os.Bundle
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
                        composable("main") { MainScreen(innerPadding, navController) }
                        composable("dataList/{tableName}") { backStackEntry ->
                            val tableName = backStackEntry.arguments?.getString("tableName")
                            DataListScreen(database, tableName, innerPadding)
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun MainScreen(innerPadding: PaddingValues, navController: NavController) {

    Column(modifier = Modifier.padding(innerPadding)) {
        val listaNombreTabla = listOf(
            "customers",
            "departments",
            "employee_projects",
            "employees",
            "order_items",
            "orders",
            "projects"
        )

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

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {
            listaNombreTabla.forEach { nombre ->
                Button(
                    onClick = {
                        navController.navigate("dataList/$nombre")
                    },
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
    }
}

@Composable
fun DataListScreen(db: AppDatabase, tableName: String?, innerPadding: PaddingValues) {
    val data = produceState(initialValue = emptyList<Any>(), key1 = tableName) {
        value = when (tableName) {
            "customers" -> db.customerDao().getAllCustomers()
            "departments" -> db.departmentDao().getAllDepartments()
            "employee_projects" -> db.employeeProjectDao().getAllEmployeeProjects()
            "employees" -> db.employeeDao().getAllEmployees()
            "order_items" -> db.orderItemDao().getAllOrderItems()
            "orders" -> db.orderDao().getAllOrders()
            "projects" -> db.projectDao().getAllProjects()
            else -> emptyList()
        }
    }.value

    Column(modifier = Modifier.padding(innerPadding)) {
        Text("Tabla: $tableName", fontSize = 24.sp, modifier = Modifier.padding(16.dp))
        when (tableName) {
            "customers" -> TableCustomer(data as List<Customer>)
            "departments" -> TableDepartment(data as List<Department>)
            "employee_projects" -> TableEmployeeProject(data as List<EmployeeProject>)
            "employees" -> TableEmployee(data as List<Employee>)
            "order_items" -> TableOrderItem(data as List<OrderItem>)
            "orders" -> TableOrder(data as List<Order>)
            "projects" -> TableProject(data as List<Project>)
            else -> LazyColumn {
                items(data) { item ->
                    Text(item.toString(), modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

@Composable
fun TableCustomer(customers: List<Customer>) {
    val columns = 4
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns)
    ) {
        listOf("ID", "Nombre", "Email", "Teléfono").forEach {
            item {
                Text(it, modifier = Modifier
                    .padding(8.dp)
                )

            }
        }

        item(span = { GridItemSpan(columns) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
        }


        customers.forEach { customer ->
            listOf(
                customer.customer_id.toString(),
                customer.customer_name,
                customer.contact_email,
                customer.contact_phone
            ).forEach { value ->
                item {
                    Text(
                        value,
                        modifier = Modifier
                            .border(1.dp, Color.Gray)
                            .padding(8.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun TableDepartment(departments: List<Department>) {
    val columns = 3
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns)
    ) {
        listOf("ID", "Nombre", "Manager").forEach {
            item {
                Text(
                    it, modifier = Modifier
                        .padding(8.dp)
                )

            }
        }

        item(span = { GridItemSpan(columns) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
        }
        departments.forEach { department ->
            listOf(
                department.department_id.toString(),
                department.department_name,
                department.manager_id.toString()
            ).forEach { value ->
                item {
                    Text(
                        value,
                        modifier = Modifier
                            .border(1.dp, Color.Gray)
                            .padding(8.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
@Composable
fun TableEmployeeProject(employeeProjects: List<EmployeeProject>) {
    val columns = 3
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns)
    ) {
        listOf("ID Empleado", "ID Proyecto", "Horas trabajadas").forEach {
            item {
                Text(
                    it, modifier = Modifier
                        .padding(8.dp)
                )

            }
        }

        item(span = { GridItemSpan(columns) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
        }
        employeeProjects.forEach { employeeProject ->
            listOf(
                employeeProject.employee_id.toString(),
                employeeProject.project_id.toString(),
                employeeProject.hours_worked.toString()
            ).forEach { value ->
                item {
                    Text(
                        value,
                        modifier = Modifier
                            .border(1.dp, Color.Gray)
                            .padding(8.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
@Composable
fun TableEmployee(employees: List<Employee>) {
    val columns = 7
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns)
    ) {
        listOf("ID", "Nombre", "Apellido", "ID Departamento", "Fecha de contratación", "Salario", "Posición").forEach {
            item {
                Text(
                    it, modifier = Modifier
                        .padding(8.dp)
                )

            }
        }

        item(span = { GridItemSpan(columns) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
        }
        employees.forEach { employee ->
            listOf(
                employee.employee_id.toString(),
                employee.first_name,
                employee.last_name,
                employee.department_id.toString(),
                employee.hire_date,
                employee.salary.toString(),
                employee.position
            ).forEach { value ->
                item {
                    Text(
                        value,
                        modifier = Modifier
                            .border(1.dp, Color.Gray)
                            .padding(8.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
@Composable
fun TableOrderItem(orderItems: List<OrderItem>) {
    val columns = 2
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns)
    ) {
        listOf("ID", "ID Pedido").forEach {
            item {
                Text(
                    it, modifier = Modifier
                        .padding(8.dp)
                )

            }
        }

        item(span = { GridItemSpan(columns) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
        }
        orderItems.forEach { orderItem ->
            listOf(
                orderItem.order_item_id.toString(),
                orderItem.order_id.toString()
            ).forEach { value ->
                item {
                    Text(
                        value,
                        modifier = Modifier
                            .border(1.dp, Color.Gray)
                            .padding(8.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
@Composable
fun TableOrder(orders: List<Order>) {
    val columns = 4
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns)
    ) {
        listOf("ID", "ID Cliente", "Fecha de pedido", "Cantidad").forEach {
            item {
                Text(
                    it, modifier = Modifier
                        .padding(8.dp)
                )

            }
        }

        item(span = { GridItemSpan(columns) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
        }
        orders.forEach { order ->
            listOf(
                order.order_id.toString(),
                order.customer_id.toString(),
                order.order_date,
                order.amount.toString()
            ).forEach { value ->
                item {
                    Text(
                        value,
                        modifier = Modifier
                            .border(1.dp, Color.Gray)
                            .padding(8.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
@Composable
fun TableProject(projects: List<Project>) {
    val columns = 6
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns)
    ) {
        listOf("ID", "Nombre", "ID Departamento", "Presupuesto", "Fecha de inicio", "Fecha de fin").forEach {
            item {
                Text(
                    it, modifier = Modifier
                        .padding(8.dp)
                )

            }
        }

        item(span = { GridItemSpan(columns) }) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black)
            )
        }
        projects.forEach { project ->
            listOf(
                project.project_id.toString(),
                project.project_name,
                project.department_id.toString(),
                project.budget.toString(),
                project.start_date,
                project.end_date
            ).forEach { value ->
                item {
                    Text(
                        value,
                        modifier = Modifier
                            .border(1.dp, Color.Gray)
                            .padding(8.dp)
                            .fillMaxWidth(),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}
