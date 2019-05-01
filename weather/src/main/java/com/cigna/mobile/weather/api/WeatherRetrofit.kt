package com.cigna.mobile.weather.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

fun getRetrofit() : Retrofit {
    return Retrofit.Builder()
        .client(OkHttpClient().newBuilder().build())
        .baseUrl("https://api.weather.gov/")
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

val weatherRetrofitModule = module {
    single { getRetrofit().create(WeatherApi::class.java) }
}