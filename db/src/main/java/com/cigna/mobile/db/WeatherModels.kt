package com.cigna.mobile.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class WeatherContextResponse(
    val properties: WeatherProperties
){
    fun getPeriods() = properties.periods
}

data class WeatherProperties(
    val periods: List<WeatherPeriods>
)

@Entity
data class WeatherPeriods (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val name: String?,
    @ColumnInfo(name = "is_daytime")
    val isDaytime: Boolean,
    val temperature: Int,
    @ColumnInfo(name = "temperature_unit")
    val temperatureUnit: String?,
    @ColumnInfo(name = "wind_speed")
    val windSpeed: String?,
    @ColumnInfo(name = "wind_direction")
    val windDirection: String?,
    @ColumnInfo(name = "short_forecast")
    val shortForecast: String?,
    @ColumnInfo(name = "detailed_forecast")
    val detailedForecast: String?
)
