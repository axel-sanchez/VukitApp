package com.example.vukitapp.data.repository

import com.example.vukitapp.data.model.MyResponse
import com.example.vukitapp.data.source.AuthLocalSource
import com.example.vukitapp.data.source.AuthRemoteSource
import com.example.vukitapp.domain.repository.AuthRepository
import com.example.vukitapp.helpers.Constants
import com.example.vukitapp.helpers.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
@Singleton
class AuthRepositoryImpl @Inject constructor(private val authRemoteSource: AuthRemoteSource,
                                             private val authLocalSource: AuthLocalSource
) : AuthRepository {
    override suspend fun getAuth(hostname:String, serial: String): Either<Constants.ApiError, MyResponse?> {
        val response = authRemoteSource.getAuth(hostname, serial).value ?: Either.Left(Constants.ApiError.API_ERROR)
        addCredentialsInSharePreference(response)
        return response
    }

    private suspend fun addCredentialsInSharePreference(result: Either<Constants.ApiError, MyResponse?>) {
        result.fold(
            left = {},
            right = {
                CoroutineScope(Dispatchers.Main).launch {
                    authLocalSource.saveCredentials(it?.data?.activation?.api_key, it?.data?.activation?.device_id)
                }
            }
        )
    }
}