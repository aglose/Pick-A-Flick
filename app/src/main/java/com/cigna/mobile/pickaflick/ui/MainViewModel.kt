package com.cigna.mobile.pickaflick.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cigna.mobile.movie.data.MovieRepository
import com.cigna.mobile.weather.data.WeatherContext
import com.cigna.mobile.weather.data.WeatherRepository
import kotlinx.coroutines.launch

class MainViewModel(private val movieRepository: MovieRepository,
                    private val weatherRepository: WeatherRepository
) : ViewModel() {


}