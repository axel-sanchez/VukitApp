package com.example.vukitapp.data.model

data class Category(
    val active: String,
    val color: String,
    val hide_menu: Int,
    val id: Int,
    val image: String,
    val name: String,
    val order: Int,
    val parent_id: Int,
    val schedules: List<Schedule>,
    val updated_at: String,
    var products: MutableList<Product?> = mutableListOf()
)