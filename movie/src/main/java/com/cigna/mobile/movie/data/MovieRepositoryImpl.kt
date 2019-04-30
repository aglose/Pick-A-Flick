package com.cigna.mobile.movie.data

import com.cigna.mobile.movie.api.TmdbApi

class MovieRepositoryImpl(private val api : TmdbApi,
                          private val db: MovieDao
) : MovieRepository {

}