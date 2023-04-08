package com.example.vukitapp.data.model

data class Terminal(
    val active: Int,
    val auth_key: String,
    val created_at: String,
    val customer_id: Int,
    val id: Int,
    val name: String,
    val print_this: Int,
    val register_id: String,
    val tpn: String,
    val type: String,
    val updated_at: String
)