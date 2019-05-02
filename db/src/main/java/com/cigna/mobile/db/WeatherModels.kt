package com.cigna.mobile.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity
data class WeatherContext(
    @PrimaryKey(autoGenerate = true)
    val dbId: Int,
    @Embedded
    val properties: WeatherProperties?
){
    @Ignore
    fun getWeather() = properties?.periods?.get(0)
}

@Entity
data class WeatherProperties(
    val forecast: String?,
    val forecastHourly: String?,
    @Embedded
    val relativeLocation: RelativeLocation?,
    val timeZone: String?,
    val radarStation: String?,
    @Embedded
    val periods: ArrayList<WeatherPeriods>?
)

@Entity
data class RelativeLocation (
    @Embedded
    val properties: LocationProperties
)

@Entity
data class LocationProperties (
    val city: String,
    val state: String
)

@Entity
data class WeatherPeriods (
    val name: String,
    val isDaytime: Boolean,
    val temperature: Int,
    val temperatureUnit: String,
    val windSpeed: String,
    val windDirection: String,
    val shortForecast: String,
    val detailedForecast: String
)
