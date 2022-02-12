package com.example.employeeapp.data.remote

import com.example.employeeapp.data.entities.Employee
import retrofit2.Response
import retrofit2.http.GET

interface EmployeeService {

    @GET("5d565297300000680030a986")
    suspend fun getEmployees() : Response<List<Employee>>

    @GET("5d565297300000680030a986")
    suspend fun getEmployee(id: Int) : Response<Employee>

//    @GET("5d565297300000680030a986")
//    suspend fun getEmployees() : Response<List<Employee>>
}