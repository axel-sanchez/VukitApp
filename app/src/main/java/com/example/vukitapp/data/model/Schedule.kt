package com.example.vukitapp.data.model

data class Schedule(
    val category_id: Int,
    val close: String,
    val created_at: String,
    val day_of_week: Int,
    val id: Int,
    val `open`: String,
    val updated_at: String
)