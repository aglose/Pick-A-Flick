package com.cigna.mobile.movie.api

// Data Model for the Response returned from the TMDB Api
data class TmdbMovieResponse(
    val results: List<com.cigna.mobile.db.TmdbMovie>
)