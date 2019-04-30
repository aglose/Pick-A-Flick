package com.cigna.mobile.weather.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WeatherContext(
    @PrimaryKey
    val id: Int,
    @Embedded
    val properties: WeatherProperties
)

@Entity
data class WeatherProperties(
    val forecast: String,
    val forecastHourly: String,
    @Embedded
    val relativeLocation: RelativeLocation,
    val timeZone: String,
    val radarStation: String
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
