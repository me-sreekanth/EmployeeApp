package com.example.employeeapp.data.repository

import com.example.employeeapp.data.local.EmployeeDao
import com.example.employeeapp.data.remote.EmployeeRemoteDataSource
import com.example.employeeapp.utils.performGetOperation
import javax.inject.Inject

class EmployeeRepository @Inject constructor(
    private val remoteDataSource: EmployeeRemoteDataSource,
    private val localDataSource: EmployeeDao
) {

    fun getEmployee(id: Int) = localDataSource.getEmployee(id)
    fun searchEmployee(query: String?) = localDataSource.searchEmployee(query)
    fun getStoredEmployees() = localDataSource.getEmployees()

    fun getEmployees() = performGetOperation(
        databaseQuery = { localDataSource.getEmployees() },
        networkCall = { remoteDataSource.getEmployees() },
        saveCallResult = { localDataSource.insertAll(it) }
    )
}