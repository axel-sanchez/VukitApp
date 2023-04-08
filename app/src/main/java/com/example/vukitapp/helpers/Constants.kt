package com.example.vukitapp.helpers

object Constants {
    const val BASE_URL = "https://staging.otfpos.dev/api/v3/"

    enum class ApiError(var error: String) {
        API_ERROR("Error al obtener las recetas")
    }

    const val API_KEY = "ApiKey"
    const val DEVICE_ID = "DeviceId"

    const val serial = "35ab1234567890"
}