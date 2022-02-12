package com.example.employeeapp.ui.employees

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.employeeapp.data.entities.Employee
import com.example.employeeapp.data.repository.EmployeeRepository

class EmployeeViewModel @ViewModelInject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    var employees = repository.getEmployees()
    
    fun searchEmployee(query: String?): LiveData<List<Employee>> {
        return repository.searchEmployee(query)
    }
}
