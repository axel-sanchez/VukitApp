package com.example.vukitapp.data.model

data class Data(
    val activation: Activation,
    val customer: Customer,
    val langs: List<Lang>,
    val magensa_keys: MagensaKeys,
    val terminals: List<Terminal>,
    val venue: Venue
)