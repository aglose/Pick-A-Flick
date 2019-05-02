package com.cigna.mobile.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MovieDao {
    @Query("SELECT * FROM tmdbmovie")
    suspend fun getAll() : List<TmdbMovie>

    @Insert
    suspend fun insertAll(vararg users: TmdbMovie)

    @Delete
    suspend fun delete(user: TmdbMovie)
}