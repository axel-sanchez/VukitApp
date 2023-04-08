package com.example.vukitapp.domain.repository

import com.example.vukitapp.data.model.MyResponse
import com.example.vukitapp.helpers.Constants
import com.example.vukitapp.helpers.Either

interface AuthRepository {
    suspend fun getAuth(hostname: String, serial: String): Either<Constants.ApiError, MyResponse?>
}