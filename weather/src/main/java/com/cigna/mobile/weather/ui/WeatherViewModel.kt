package com.cigna.mobile.weather.ui

import androidx.lifecycle.MutableLiveData
import com.cigna.mobile.db.WeatherPeriods
import com.cigna.mobile.shared.BaseViewModel
import com.cigna.mobile.weather.data.WeatherRepository

class WeatherViewModel(private val weatherRepository: WeatherRepository) : BaseViewModel() {

    private val weatherLiveData: MutableLiveData<Result<List<WeatherPeriods>>> = MutableLiveData()

//    fun getWeatherForLatLng(latLng: LatLng) : LiveData<List<WeatherPeriods>> {
//        return weatherLiveData.callRepo {
//            weatherRepository.getWeatherForLocationAsync(latLng, weatherLiveData)
//        }
//    }
}