package com.cigna.mobile.weather.data

import androidx.lifecycle.MutableLiveData
import com.cigna.mobile.db.WeatherDao
import com.cigna.mobile.db.WeatherPeriods
import com.cigna.mobile.shared.IOResponse
import com.cigna.mobile.shared.standardApiCall
import com.cigna.mobile.weather.api.WeatherApi
import retrofit2.Response

interface WeatherRepository {
    suspend fun getWeatherForLocationAsync(
        latLng: LatLng,
        weatherLiveData: MutableLiveData<IOResponse<List<WeatherPeriods>>>
    )
}

class WeatherRepositoryImpl(private val api: WeatherApi,
                            private val dao: WeatherDao
) : WeatherRepository {

    override suspend fun getWeatherForLocationAsync(
        latLng: LatLng,
        weatherLiveData: MutableLiveData<IOResponse<List<WeatherPeriods>>>
    ) {
        val completed = standardApiCall(
            dbCall = { dao.getAll() },
            apiCall = {
                try {
                    val response = api.getWeatherByLatLngAsync(latLng.lat, latLng.lng).execute()
                    if(response.isSuccessful){
                        val forecastUrl = response.body()?.
                            getAsJsonObject("properties")?.
                            getAsJsonPrimitive("forecast")?.asString
                        val forecastResponse = api.getWeatherForecast(forecastUrl.toString()).execute()
                        forecastResponse as Response<Any>
                    }else{
                        response as Response<Any>
                    }
                }catch (e: Exception){
                    null
                }
            },
            networkCallSuccess = {
                dao.deleteAll()
                dao.insertAll(it)
            }
        )
        weatherLiveData.postValue(completed)
    }
}