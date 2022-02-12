package com.example.employeeapp.data.remote

import javax.inject.Inject

class EmployeeRemoteDataSource @Inject constructor(
    private val characterService: EmployeeService
): BaseDataSource() {

//    suspend fun getCharacters() = getResult { characterService.getAllCharacters() }
    suspend fun getEmployee(id: Int) = getResult { characterService.getEmployee(id) }
    suspend fun getEmployees() = getResult { characterService.getEmployees() }
}