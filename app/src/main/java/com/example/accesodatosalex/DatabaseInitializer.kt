package com.example.accesodatosalex

import java.io.BufferedReader
import java.io.InputStreamReader
import android.content.Context
import com.example.accesodatosalex.ui.theme.AppDatabase
import com.example.accesodatosalex.entity.*

object DatabaseInitializer {

    suspend fun initializeDatabase(context: Context, database: AppDatabase) {
        // Inicializar DAOs
        val customerDao = database.customerDao()
        val departmentDao = database.departmentDao()
        val employeeProjectDao = database.employeeProjectDao()
        val employeeDao = database.employeeDao()
        val orderItemDao = database.orderItemDao()
        val orderDao = database.orderDao()
        val projectDao = database.projectDao()

        // Inicializar cada tabla si está vacía
        if (customerDao.getAllCustomers().isEmpty()) {
            val customers = importCustomersFromCsv(context)
            customerDao.insertCustomers(customers)
        }

        if (departmentDao.getAllDepartments().isEmpty()) {
            val departments = importDepartmentsFromCsv(context)
            departmentDao.insertDepartments(departments)
        }

        if (employeeProjectDao.getAllEmployeeProjects().isEmpty()) {
            val employeeProjects = importEmployeeProjectsFromCsv(context)
            employeeProjectDao.insertEmployeeProjects(employeeProjects)
        }

        if (employeeDao.getAllEmployees().isEmpty()) {
            val employees = importEmployeesFromCsv(context)
            employeeDao.insertEmployees(employees)
        }

        if (orderItemDao.getAllOrderItems().isEmpty()) {
            val orderItems = importOrderItemsFromCsv(context)
            orderItemDao.insertOrderItems(orderItems)
        }

        if (orderDao.getAllOrders().isEmpty()) {
            val orders = importOrdersFromCsv(context)
            orderDao.insertOrders(orders)
        }

        if (projectDao.getAllProjects().isEmpty()) {
            val projects = importProjectsFromCsv(context)
            projectDao.insertProjects(projects)
        }
    }

    private fun importCustomersFromCsv(context: Context): List<Customer> {
        val customers = mutableListOf<Customer>()
        val inputStream = context.assets.open("customers.csv")
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            reader.readLine() // Ignorar la cabecera
            reader.forEachLine { line ->
                val columns = line.split(",")
                val customer = Customer(
                    customer_id = columns[0].toInt(),
                    customer_name = columns[1],
                    contact_email = columns[2],
                    contact_phone = columns[3]
                )
                customers.add(customer)
            }
        }
        return customers
    }

    private fun importDepartmentsFromCsv(context: Context): List<Department> {
        val departments = mutableListOf<Department>()
        val inputStream = context.assets.open("departments.csv")
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            reader.readLine() // Ignorar la cabecera
            reader.forEachLine { line ->
                val columns = line.split(",")
                val department = Department(
                    department_id = columns[0].toInt(),
                    department_name = columns[1],
                    manager_id = columns[2].toInt()
                )
                departments.add(department)
            }
        }
        return departments
    }

    private fun importEmployeeProjectsFromCsv(context: Context): List<EmployeeProject> {
        val employeeProjects = mutableListOf<EmployeeProject>()
        val inputStream = context.assets.open("employee_projects.csv")
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            reader.readLine() // Ignorar la cabecera
            reader.forEachLine { line ->
                val columns = line.split(",")
                val employeeProject = EmployeeProject(
                    employee_id = columns[0].toInt(),
                    project_id = columns[1].toInt(),
                    hours_worked = columns[2].toFloat()
                )
                employeeProjects.add(employeeProject)
            }
        }
        return employeeProjects
    }

    private fun importEmployeesFromCsv(context: Context): List<Employee> {
        val employees = mutableListOf<Employee>()
        val inputStream = context.assets.open("employees_realistic.csv")
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            reader.readLine() // Ignorar la cabecera
            reader.forEachLine { line ->
                val columns = line.split(",")
                val employee = Employee(
                    employee_id = columns[0].toInt(),
                    first_name = columns[1],
                    last_name = columns[2],
                    department_id = columns[3].toInt(),
                    hire_date = columns[4],
                    salary = columns[5].toFloat(),
                    position = columns[6]
                )
                employees.add(employee)
            }
        }
        return employees
    }

    private fun importOrderItemsFromCsv(context: Context): List<OrderItem> {
        val orderItems = mutableListOf<OrderItem>()
        val inputStream = context.assets.open("order_items.csv")
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            reader.readLine() // Ignorar la cabecera
            reader.forEachLine { line ->
                val columns = line.split(",")
                val orderItem = OrderItem(
                    order_item_id = columns[0].toInt(),
                    order_id = columns[1].toInt(),
                    product_name = columns[2],
                    quantity = columns[3].toInt(),
                    price = columns[4].toFloat()
                )
                orderItems.add(orderItem)
            }
        }
        return orderItems
    }

    private fun importOrdersFromCsv(context: Context): List<Order> {
        val orders = mutableListOf<Order>()
        val inputStream = context.assets.open("orders.csv")
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            reader.readLine() // Ignorar la cabecera
            reader.forEachLine { line ->
                val columns = line.split(",")
                val order = Order(
                    order_id = columns[0].toInt(),
                    customer_id = columns[1].toInt(),
                    order_date = columns[2],
                    amount = columns[3].toFloat()
                )
                orders.add(order)
            }
        }
        return orders
    }

    private fun importProjectsFromCsv(context: Context): List<Project> {
        val projects = mutableListOf<Project>()
        val inputStream = context.assets.open("projects.csv")
        BufferedReader(InputStreamReader(inputStream)).use { reader ->
            reader.readLine() // Ignorar la cabecera
            reader.forEachLine { line ->
                val columns = line.split(",")
                val project = Project(
                    project_id = columns[0].toInt(),
                    project_name = columns[1],
                    department_id = columns[2].toInt(),
                    budget = columns[3].toFloat(),
                    start_date = columns[4],
                    end_date = columns[5]
                )
                projects.add(project)
            }
        }
        return projects
    }
}
