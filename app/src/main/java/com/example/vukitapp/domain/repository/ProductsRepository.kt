package com.example.vukitapp.domain.repository

import com.example.vukitapp.data.model.MyResponse2
import com.example.vukitapp.helpers.Constants
import com.example.vukitapp.helpers.Either

interface ProductsRepository {
    suspend fun getProducts(): Either<Constants.ApiError, MyResponse2?>
}