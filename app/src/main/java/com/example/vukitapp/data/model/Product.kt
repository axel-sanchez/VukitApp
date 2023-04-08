package com.example.vukitapp.data.model

data class Product(
    val active: Int,
    val alias: String,
    val description: String,
    val id: Int,
    val is_shortcut: Int,
    val labels: String,
    val name: String,
    val order: Int,
    val price_type: String,
    val recommended: Int,
    val reviews: Int,
    val service_charge_active: Int,
    val short_description: String,
    val sku: String,
    val updated_at: String,
    val use_default_tax: Int
)