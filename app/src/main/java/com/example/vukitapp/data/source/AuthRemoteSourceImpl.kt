package com.example.vukitapp.data.source

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.vukitapp.data.model.Activation
import com.example.vukitapp.data.model.MyResponse
import com.example.vukitapp.data.service.ApiServiceProduct
import com.example.vukitapp.helpers.Constants
import com.example.vukitapp.helpers.Constants.ApiError.*
import com.example.vukitapp.helpers.Either
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface AuthRemoteSource {
    suspend fun getAuth(hostname: String, serial: String): MutableLiveData<Either<Constants.ApiError, MyResponse?>>
}

@Singleton
class AuthRemoteSourceImpl @Inject constructor(private val service: ApiServiceProduct) : AuthRemoteSource {
    override suspend fun getAuth(hostname: String, serial: String): MutableLiveData<Either<Constants.ApiError, MyResponse?>> {
        val mutableLiveData = MutableLiveData<Either<Constants.ApiError, MyResponse?>>()

        try {
            val response = service.activateLicense(
                Activation(
                    device_id = "f5293f74-1ee4-b485-b69c-ad589f26184b",
                    hostname = hostname,
                    serial = serial
                )
            )
            if (response.isSuccessful) {
                Log.i("Successful Response", response.body().toString())

                response.body()?.let { products ->
                    mutableLiveData.value = Either.Right(products)
                } ?: kotlin.run {
                    mutableLiveData.value = Either.Left(API_ERROR)
                }
            } else {
                Log.i("Error Response", response.errorBody().toString())
                val apiError = API_ERROR
                apiError.error = response.message()
                mutableLiveData.value = Either.Left(apiError)
            }
        } catch (e: Exception) {
            mutableLiveData.value = Either.Left(API_ERROR)
            Log.e(
                "ProductRemoteSourceImpl",
                e.message?:"Error al obtener la autenticación"
            )
            e.printStackTrace()
        }

        return mutableLiveData
    }
}