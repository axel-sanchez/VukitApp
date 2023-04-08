package com.example.vukitapp.domain.usecase

import com.example.vukitapp.data.model.MyResponse
import com.example.vukitapp.domain.repository.AuthRepository
import com.example.vukitapp.helpers.Constants
import com.example.vukitapp.helpers.Either
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface GetAuthUseCase{
    suspend fun call(hostname: String, serial: String): Either<Constants.ApiError, MyResponse?>
}

@Singleton
class GetAuthUseCaseImpl @Inject constructor(private val repository: AuthRepository): GetAuthUseCase {
    override suspend fun call(hostname: String, serial: String): Either<Constants.ApiError, MyResponse?> {
        return repository.getAuth(hostname, serial)
    }
}