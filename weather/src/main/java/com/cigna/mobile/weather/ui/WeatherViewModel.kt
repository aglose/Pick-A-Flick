package com.cigna.mobile.weather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cigna.mobile.shared.BaseViewModel
import com.cigna.mobile.weather.data.LatLng
import com.cigna.mobile.db.WeatherContext
import com.cigna.mobile.weather.data.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : BaseViewModel() {

    private val weatherLiveData: MutableLiveData<WeatherContext> = MutableLiveData()

    fun getWeatherForLatLng(latLng: LatLng) : LiveData<WeatherContext> {
        loadingLiveData.value = true
        viewModelScope.launch {
            val weather = weatherRepository.getWeatherForLocationAsync(latLng)
            loadingLiveData.value = false
            if(weather.isSuccessful()){
                weatherLiveData.value = weather.result
            }else{
                errorLiveData.value = weather.errorCode
            }
        }
        return weatherLiveData
    }
}