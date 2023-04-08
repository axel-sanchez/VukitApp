package com.example.vukitapp.data.source

import android.content.SharedPreferences
import com.example.vukitapp.helpers.Constants.API_KEY
import com.example.vukitapp.helpers.Constants.DEVICE_ID
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Axel Sanchez
 */
interface AuthLocalSource {
    suspend fun saveCredentials(apiKey: String?, deviceId: String?)
    suspend fun getCredentials(): Map<String, String>
}

@Singleton
class AuthLocalSourceImpl @Inject constructor(private val sharedPreferences: SharedPreferences): AuthLocalSource{
    override suspend fun saveCredentials(apiKey: String?, deviceId: String?) {
        with(sharedPreferences.edit()){
            this.putString(API_KEY, apiKey)
            this.putString(DEVICE_ID, deviceId)
            this.apply()
        }
    }

    override suspend fun getCredentials(): Map<String, String> {
        val map = mutableMapOf<String, String>()
        map[API_KEY] = sharedPreferences.getString(API_KEY, "").toString()
        map[DEVICE_ID] = sharedPreferences.getString(DEVICE_ID, "").toString()
        return map
    }
}