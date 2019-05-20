package com.cigna.mobile.weather.api

import com.cigna.mobile.db.WeatherContextResponse
import com.google.gson.JsonObject
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface WeatherApi {
    @GET("points/{lat},{lng}")
    fun getWeatherByLatLngAsync(
        @Path("lat") lat: Double,
        @Path("lng") lng: Double
    ): Deferred<Response<JsonObject>>
    @GET
    fun getWeatherForecast(@Url url: String): Deferred<Response<WeatherContextResponse>>
}