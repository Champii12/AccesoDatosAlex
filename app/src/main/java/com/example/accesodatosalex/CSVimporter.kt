package com.example.accesodatosalex

import android.content.Context
import com.example.accesodatosalex.entity.*
import java.io.BufferedReader
import java.io.InputStreamReader

object CsvImporter {

    fun importCustomersFromCsv(context: Context): List<Customer> {
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

    fun importDepartmentsFromCsv(context: Context): List<Department> {
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

    fun importEmployeeProjectsFromCsv(context: Context): List<EmployeeProject> {
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

    fun importEmployeesFromCsv(context: Context): List<Employee> {
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

    fun importOrderItemsFromCsv(context: Context): List<OrderItem> {
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

    fun importOrdersFromCsv(context: Context): List<Order> {
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

    fun importProjectsFromCsv(context: Context): List<Project> {
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
