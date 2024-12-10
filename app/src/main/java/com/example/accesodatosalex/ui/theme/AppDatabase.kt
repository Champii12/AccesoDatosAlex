package com.example.accesodatosalex.ui.theme

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.accesodatosalex.entity.Customer
import com.example.accesodatosalex.entity.Department
import com.example.accesodatosalex.entity.EmployeeProject
import com.example.accesodatosalex.entity.Employee
import com.example.accesodatosalex.entity.OrderItem
import com.example.accesodatosalex.entity.Order
import com.example.accesodatosalex.entity.Project
import com.example.accesodatosalex.dao.CustomerDao
import com.example.accesodatosalex.dao.DepartmentDao
import com.example.accesodatosalex.dao.EmployeeProjectDao
import com.example.accesodatosalex.dao.EmployeeDao
import com.example.accesodatosalex.dao.OrderItemDao
import com.example.accesodatosalex.dao.OrderDao
import com.example.accesodatosalex.dao.ProjectDao


@Database(
    entities = [
        Customer::class,
        Department::class,
        EmployeeProject::class,
        Employee::class,
        OrderItem::class,
        Order::class,
        Project::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun departmentDao(): DepartmentDao
    abstract fun employeeProjectDao(): EmployeeProjectDao
    abstract fun employeeDao(): EmployeeDao
    abstract fun orderItemDao(): OrderItemDao
    abstract fun orderDao(): OrderDao
    abstract fun projectDao(): ProjectDao
}
