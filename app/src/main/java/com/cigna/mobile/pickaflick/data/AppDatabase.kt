package com.cigna.mobile.pickaflick.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cigna.mobile.movie.data.MovieDao
import com.cigna.mobile.movie.data.TmdbMovie
import com.cigna.mobile.weather.data.WeatherContext
import com.cigna.mobile.weather.data.WeatherDao


@Database(entities = [TmdbMovie::class, WeatherContext::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun weatherDao(): WeatherDao
}