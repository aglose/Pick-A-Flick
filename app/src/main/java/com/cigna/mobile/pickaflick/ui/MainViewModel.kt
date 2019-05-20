package com.cigna.mobile.pickaflick.ui

import androidx.lifecycle.ViewModel
import com.cigna.mobile.movie.data.MovieRepository
import com.cigna.mobile.weather.data.WeatherRepository

class MainViewModel(
    private val movieRepository: MovieRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel()