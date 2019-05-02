package com.cigna.mobile.movie.api

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbApi{
    @GET("movie/popular")
    fun getPopularMovies() : Deferred<TmdbMovieResponse>
    @GET("movie/{id}")
    fun getMovieById(@Path("id") id:Int): Deferred<com.cigna.mobile.db.TmdbMovie>
}