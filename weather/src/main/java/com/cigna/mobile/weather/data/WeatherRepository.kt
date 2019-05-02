package com.cigna.mobile.weather.data

import com.cigna.mobile.db.WeatherContext
import com.cigna.mobile.db.WeatherDao
import com.cigna.mobile.shared.IOResponse
import com.cigna.mobile.shared.standardApiCall

interface WeatherRepository {
    suspend fun getWeatherForLocationAsync(latLng: LatLng): IOResponse<WeatherContext>
}

class WeatherRepositoryImpl(private val api: com.cigna.mobile.weather.api.WeatherApi,
                            private val dao: WeatherDao
) : WeatherRepository {

    override suspend fun getWeatherForLocationAsync(latLng: LatLng): IOResponse<WeatherContext> {
        return standardApiCall(
            dbCall = { val list = dao.getAll(); if (list.isEmpty()) null else list[0] },
            apiCall = {
                val response = api.getWeatherByLatLngAsync(latLng.lat, latLng.lng).execute()
                if (response.isSuccessful) {
                    api.getWeatherForecast(response.body()?.properties?.forecast!!).execute()
                } else {
                    response
                }
            },
            networkCallSuccess = { if (it.properties?.forecast == null) dao.replace(it) }
        )
    }
}