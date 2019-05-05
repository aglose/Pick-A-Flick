package com.cigna.mobile.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cigna.mobile.db.WeatherPeriods
import com.cigna.mobile.shared.BaseViewModel
import com.cigna.mobile.shared.IOResponse
import com.cigna.mobile.weather.data.LatLng
import com.cigna.mobile.weather.data.WeatherRepository

class WeatherViewModel(private val weatherRepository: WeatherRepository) : BaseViewModel() {

    private val weatherLiveData: MutableLiveData<IOResponse<List<WeatherPeriods>>> = MutableLiveData()

    fun getWeatherForLatLng(latLng: LatLng) : LiveData<List<WeatherPeriods>> {
        return weatherLiveData.callRepo {
            weatherRepository.getWeatherForLocationAsync(latLng, weatherLiveData)
        }
    }
}