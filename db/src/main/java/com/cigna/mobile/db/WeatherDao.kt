package com.cigna.mobile.db

import androidx.room.*


@Dao
interface WeatherDao {
    @Query("SELECT * FROM weathercontext")
    suspend fun getAll() : List<WeatherContext>

    @Insert
    suspend fun insertAll(vararg weather: WeatherContext)

    @Transaction
    suspend fun replace(weather: WeatherContext){
        deleteAll()
        insertAll(weather)
    }

    @Query("DELETE FROM weathercontext")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(user: WeatherContext)

}
