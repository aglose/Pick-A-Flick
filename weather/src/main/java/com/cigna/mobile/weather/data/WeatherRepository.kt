package com.cigna.mobile.weather.data

import androidx.lifecycle.MutableLiveData
import com.cigna.mobile.db.WeatherDao
import com.cigna.mobile.db.WeatherPeriods
import com.cigna.mobile.shared.fullServiceCall
import com.cigna.mobile.weather.api.WeatherApi

interface WeatherRepository {
    suspend fun getWeatherForLocationAsync(
        latLng: LatLng,
        weatherLiveData: MutableLiveData<com.cigna.mobile.shared.Result<List<WeatherPeriods>>>
    )
}

class WeatherRepositoryImpl(
    private val api: WeatherApi,
    private val dao: WeatherDao
) : WeatherRepository {

    override suspend fun getWeatherForLocationAsync(
        latLng: LatLng,
        weatherLiveData: MutableLiveData<com.cigna.mobile.shared.Result<List<WeatherPeriods>>>
    ) {
        val completed = fullServiceCall(
            dbCall = { dao.getAll() },
            apiCall = {
                api.getWeatherByLatLngAsync(latLng.lat, latLng.lng).await()
            }
        )
        weatherLiveData.postValue(completed)
    }
}