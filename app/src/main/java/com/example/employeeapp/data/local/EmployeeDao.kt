package com.example.employeeapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.employeeapp.data.entities.Employee

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee")
    fun getEmployees() : LiveData<List<Employee>>

    @Query("SELECT * FROM employee WHERE name LIKE :query OR email LIKE :query")
    fun searchEmployee(query : String?) :LiveData<List<Employee>>

    @Query("SELECT * FROM employee WHERE id = :id")
    fun getEmployee(id: Int): LiveData<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(employees: List<Employee>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(employee: Employee)


}