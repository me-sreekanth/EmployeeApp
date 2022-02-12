package com.example.employeeapp.ui.employeedetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.employeeapp.data.entities.Employee
import com.example.employeeapp.data.repository.EmployeeRepository

class EmployeeDetailViewModel @ViewModelInject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _employee = _id.switchMap { id ->
//        repository.getCharacter(id)
        repository.getEmployee(id)
    }
    val employee: LiveData<Employee> = _employee


    fun start(id: Int) {
        _id.value = id
    }

}
