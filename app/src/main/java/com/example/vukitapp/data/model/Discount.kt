package com.example.vukitapp.data.model

data class Discount(
    val active: Int,
    val complimentary: Int,
    val created_at: String,
    val deleted_at: Any,
    val discount: Double,
    val id: Int,
    val order: Int,
    val reason: String,
    val require_pin: Int,
    val type: String,
    val updated_at: String,
    val venue_id: Int
)