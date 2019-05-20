package com.cigna.mobile.movie.data

import com.cigna.mobile.movie.api.TmdbApi
import com.cigna.mobile.db.MovieDao

class MovieRepositoryImpl(
    private val api: TmdbApi,
    private val db: MovieDao
) : MovieRepository