package com.example.vukitapp.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vukitapp.data.model.MyResponse2
import com.example.vukitapp.data.service.ApiServiceProduct
import com.example.vukitapp.helpers.Constants
import com.example.vukitapp.helpers.Either
import javax.inject.Inject

/**
 * @author Axel Sanchez
 */
interface ProductsRemoteSource{
    suspend fun getProducts(apiKey: String, deviceId: String): MutableLiveData<Either<Constants.ApiError, MyResponse2?>>
}

class ProductsRemoteSourceImpl@Inject constructor(
    private val service: ApiServiceProduct
): ProductsRemoteSource {
    override suspend fun getProducts(apiKey: String, deviceId: String): MutableLiveData<Either<Constants.ApiError, MyResponse2?>> {
        val mutableLiveData = MutableLiveData<Either<Constants.ApiError, MyResponse2?>>()

        try {
            val response = service.getProducts(
                apiKey,
                deviceId,
                "application/json"
            )
            if (response.isSuccessful) {
                Log.i("Successful Response", response.body().toString())

                response.body()?.let { products ->
                    mutableLiveData.value = Either.Right(products)
                } ?: kotlin.run {
                    mutableLiveData.value = Either.Left(Constants.ApiError.API_ERROR)
                }
            } else {
                Log.i("Error Response", response.errorBody().toString())
                val apiError = Constants.ApiError.API_ERROR
                apiError.error = response.message()
                mutableLiveData.value = Either.Left(apiError)
            }
        } catch (e: Exception) {
            mutableLiveData.value = Either.Left(Constants.ApiError.API_ERROR)
            Log.e(
                "ProductRemoteSourceImpl",
                e.message?:"Error al obtener los productos"
            )
            e.printStackTrace()
        }

        return mutableLiveData
    }
}