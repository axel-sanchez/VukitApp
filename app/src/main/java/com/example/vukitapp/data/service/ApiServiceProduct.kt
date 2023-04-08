package com.example.vukitapp.data.service

import com.example.vukitapp.data.model.Activation
import com.example.vukitapp.data.model.MyResponse
import com.example.vukitapp.data.model.MyResponse2
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * @author Axel Sanchez
 */
interface ApiServiceProduct {
    @GET("products/menu")
    suspend fun getProducts(
        @Header("api-key") apiKey: String,
        @Header("device-id") deviceId: String,
        @Header("Content-Type") contentType: String
    ): Response<MyResponse2>

    @POST("license/activate")
    suspend fun activateLicense(@Body() activation: Activation): Response<MyResponse>
}