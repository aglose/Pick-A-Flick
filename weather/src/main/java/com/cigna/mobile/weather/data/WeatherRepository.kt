package com.cigna.mobile.weather.data

interface WeatherRepository{
    suspend fun getWeatherForLocationAsync(lat: Int, lng: Int): WeatherContext
}