package com.cigna.mobile.weather.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    fun getAll() : List<WeatherContext>

    @Insert
    fun insertAll(vararg users: WeatherContext)

    @Delete
    fun delete(user: WeatherContext)
}