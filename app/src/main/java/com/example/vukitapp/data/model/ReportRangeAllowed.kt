package com.example.vukitapp.data.model

data class ReportRangeAllowed(
    val last_week: Boolean,
    val `open`: Boolean,
    val today: Boolean,
    val yesterday: Boolean
)