package com.cigna.mobile.db

import androidx.room.*

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weatherperiods")
    fun getAll(): List<WeatherPeriods>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(weather: List<WeatherPeriods>)

    @Query("DELETE FROM weatherperiods")
    fun deleteAll()

    @Delete
    fun delete(user: WeatherPeriods)
}
