package com.cigna.mobile.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cigna.mobile.weather.data.WeatherContext
import com.cigna.mobile.weather.data.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    private val weatherLiveData: MutableLiveData<WeatherContext> = MutableLiveData()

    fun getWeatherForLatLng(lat: Double, lng: Double) : LiveData<WeatherContext> {
        viewModelScope.launch {
            val weather = weatherRepository.getWeatherForLocationAsync(lat, lng)
            weatherLiveData.value = weather
        }
        return weatherLiveData
    }
}