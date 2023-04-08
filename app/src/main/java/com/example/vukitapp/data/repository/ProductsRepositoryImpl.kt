package com.example.vukitapp.data.repository

import com.example.vukitapp.data.model.Category
import com.example.vukitapp.data.model.MyResponse2
import com.example.vukitapp.data.model.ProductCategory
import com.example.vukitapp.data.source.AuthLocalSource
import com.example.vukitapp.data.source.ProductsRemoteSource
import com.example.vukitapp.domain.repository.ProductsRepository
import com.example.vukitapp.helpers.Constants
import com.example.vukitapp.helpers.Either
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
@Singleton
class ProductsRepositoryImpl @Inject constructor(
    private val productsRemoteSource: ProductsRemoteSource,
    private val authLocalSource: AuthLocalSource
) : ProductsRepository {
    override suspend fun getProducts(): Either<Constants.ApiError, MyResponse2?> {

        val credentials = authLocalSource.getCredentials()

        val response = productsRemoteSource.getProducts(credentials["ApiKey"]?:"", credentials["DeviceId"]?:"").value ?: Either.Left(Constants.ApiError.API_ERROR)

        response.fold(
            left = {},
            right = {
                it?.data?.let { data ->
                    data.categories.forEach { category: Category? ->
                        category?.products = mutableListOf()

                        data.product_category.forEach {productCategory ->
                            if(productCategory.category_id == category?.id){
                                category.products.add(data.products.find { product ->
                                    product.id ==  productCategory.product_id}
                                )
                            }
                        }
                    }
                }
            }
        )
        return response
    }
}