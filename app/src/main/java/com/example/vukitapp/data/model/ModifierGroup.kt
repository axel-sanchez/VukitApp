package com.example.vukitapp.data.model

data class ModifierGroup(
    val active: Int,
    val id: Int,
    val image: String,
    val max_allowed: Int,
    val min_required: Int,
    val name: String,
    val order: Int
)