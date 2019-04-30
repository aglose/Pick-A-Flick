package com.cigna.mobile.weather.api

import com.cigna.mobile.weather.data.WeatherContext
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi{
    @GET("points/{lat},{lng}")
    suspend fun getWeatherByLatLngAsync(@Path("lat") lat: Int, @Path("lng") lng: Int): Deferred<WeatherContext>
}