package com.cigna.mobile.weather.di

import com.cigna.mobile.weather.data.WeatherRepository
import com.cigna.mobile.weather.data.WeatherRepositoryImpl
import org.koin.dsl.module

val weatherRepositoryModule = module {
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get()) }
}