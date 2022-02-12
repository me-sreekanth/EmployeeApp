package com.example.employeeapp.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee(
    @Embedded(prefix = "prefix_address")
    val address: Address,
    @Embedded(prefix = "prefix_company")
    val company: Company?,
    val email: String,
    @PrimaryKey
    val id: Int,
    val name: String,
    val phone: String?,
    val profile_image: String?,
    val username: String?,
    val website: String?
)
