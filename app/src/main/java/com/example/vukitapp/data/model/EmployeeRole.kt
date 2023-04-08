package com.example.vukitapp.data.model

data class EmployeeRole(
    val active: Int,
    val created_at: String,
    val display_name: String,
    val id: Int,
    val name: String,
    val permissions: List<Permission>,
    val updated_at: String,
    val venue_id: Int
)