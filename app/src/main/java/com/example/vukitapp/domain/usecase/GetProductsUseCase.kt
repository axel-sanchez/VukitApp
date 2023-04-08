package com.example.vukitapp.domain.usecase

import com.example.vukitapp.data.model.MyResponse2
import com.example.vukitapp.domain.repository.ProductsRepository
import com.example.vukitapp.helpers.Constants
import com.example.vukitapp.helpers.Either
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface GetProductsUseCase{
    suspend fun call(): Either<Constants.ApiError, MyResponse2?>
}

@Singleton
class GetProductsUseCaseImpl @Inject constructor(private val repository: ProductsRepository): GetProductsUseCase {
    override suspend fun call(): Either<Constants.ApiError, MyResponse2?> {
        return repository.getProducts()
    }
}