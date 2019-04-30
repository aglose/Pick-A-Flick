package com.cigna.mobile.movie.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class TmdbMovie(
    @PrimaryKey
    val id: Int,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val title: String,
    val overview: String,
    val adult: Boolean
)