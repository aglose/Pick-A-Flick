package com.cigna.mobile.movie.api

import com.cigna.mobile.movie.data.TmdbMovie

// Data Model for the Response returned from the TMDB Api
data class TmdbMovieResponse(
    val results: List<TmdbMovie>
)