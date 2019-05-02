package com.cigna.mobile.weather.di

import com.cigna.mobile.shared.getLoggingInterceptor
import com.cigna.mobile.weather.api.WeatherApi
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun getWeatherRetrofit() : Retrofit {
    return Retrofit.Builder()
        .client(OkHttpClient.Builder().addInterceptor(getLoggingInterceptor()).build())
        .baseUrl("https://api.weather.gov/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

val weatherRetrofitModule = module {
    single { getWeatherRetrofit().create(WeatherApi::class.java) }
}
