package com.example.accesodatosalex.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.accesodatosalex.entity.Customer

@Dao
interface CustomerDao {
    // Obtener todos los clientes
    @Query("SELECT * FROM customers")
    suspend fun getAllCustomers(): List<Customer>

    // Insertar un cliente
    @Insert
    suspend fun insertCustomer(customer: Customer)

    // Insertar múltiples clientes
    @Insert
    suspend fun insertCustomers(customers: List<Customer>)

    // Buscar un cliente por ID
    @Query("SELECT * FROM customers WHERE customer_id = :id")
    suspend fun getCustomerById(id: Int): Customer?

    // Borrar todos los clientes
    @Query("DELETE FROM customers")
    suspend fun deleteAllCustomers()
}
