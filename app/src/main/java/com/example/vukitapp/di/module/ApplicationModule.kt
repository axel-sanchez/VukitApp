package com.example.vukitapp.di.module

import android.content.Context
import android.content.SharedPreferences
import com.example.vukitapp.data.repository.AuthRepositoryImpl
import com.example.vukitapp.data.repository.ProductsRepositoryImpl
import com.example.vukitapp.data.service.ApiClient
import com.example.vukitapp.data.service.ApiServiceProduct
import com.example.vukitapp.data.source.*
import com.example.vukitapp.domain.repository.AuthRepository
import com.example.vukitapp.domain.repository.ProductsRepository
import com.example.vukitapp.domain.usecase.GetAuthUseCase
import com.example.vukitapp.domain.usecase.GetAuthUseCaseImpl
import com.example.vukitapp.domain.usecase.GetProductsUseCase
import com.example.vukitapp.domain.usecase.GetProductsUseCaseImpl
import com.example.vukitapp.helpers.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideApiServiceProduct(): ApiServiceProduct {
        return ApiClient.Builder<ApiServiceProduct>()
            .setBaseUrl(BASE_URL)
            .setApiService(ApiServiceProduct::class.java)
            .build()
    }

    @Provides
    @Singleton
    fun provideProductsRepository(productsRepository: ProductsRepositoryImpl): ProductsRepository = productsRepository

    @Provides
    @Singleton
    fun provideAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository = authRepository

    @Provides
    @Singleton
    fun provideProductRemoteSource(productRemoteSource: ProductsRemoteSourceImpl): ProductsRemoteSource = productRemoteSource

    @Provides
    @Singleton
    fun provideAuthRemoteSource(authRemoteSource: AuthRemoteSourceImpl): AuthRemoteSource = authRemoteSource

    @Provides
    @Singleton
    fun provideProductLocalSource(productLocalSource: AuthLocalSourceImpl): AuthLocalSource = productLocalSource

    @Provides
    @Singleton
    fun provideGetAuthUseCase(getAuthUseCase: GetAuthUseCaseImpl): GetAuthUseCase = getAuthUseCase

    @Provides
    @Singleton
    fun provideGetProductsUseCase(getProductsUseCase: GetProductsUseCaseImpl): GetProductsUseCase = getProductsUseCase

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = context.getSharedPreferences("MiPref", Context.MODE_PRIVATE)
}