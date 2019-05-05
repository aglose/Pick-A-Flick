package com.cigna.mobile.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [TmdbMovie::class, WeatherPeriods::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun weatherDao(): WeatherDao

}