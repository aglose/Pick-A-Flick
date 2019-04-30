package com.cigna.mobile.movie.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MovieDao {
    @Query("SELECT * FROM tmdbmovie")
    fun getAll() : List<TmdbMovie>

    @Insert
    fun insertAll(vararg users: TmdbMovie)

    @Delete
    fun delete(user: TmdbMovie)
}