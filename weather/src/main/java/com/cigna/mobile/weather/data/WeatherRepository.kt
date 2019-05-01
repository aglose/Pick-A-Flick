package com.cigna.mobile.weather.data

import com.cigna.mobile.weather.api.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface WeatherRepository{
    suspend fun getWeatherForLocationAsync(lat: Double, lng: Double): WeatherContext
}

class WeatherRepositoryImpl(private val api: WeatherApi,
                            private val dao: WeatherDao
) : WeatherRepository {
    override suspend fun getWeatherForLocationAsync(lat: Double, lng: Double): WeatherContext = withContext(Dispatchers.Default) {
        api.getWeatherByLatLngAsync(lat, lng).await()
    }
}