package com.example.employeeapp.data.entities

import androidx.room.Embedded

data class Address(
    val city: String,
    @Embedded(prefix = "prefix_geo")
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
)
