package com.cigna.mobile.pickaflick.di

import com.cigna.mobile.movie.data.MovieRepository
import com.cigna.mobile.movie.data.MovieRepositoryImpl
import com.cigna.mobile.movie.di.movieRetrofitModule
import com.cigna.mobile.weather.data.WeatherRepository
import com.cigna.mobile.weather.data.WeatherRepositoryImpl
import com.cigna.mobile.weather.di.weatherRetrofitModule
import org.koin.dsl.module

val repositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            get(),
            get()
        )
    }
}

val retrofitModules = weatherRetrofitModule + movieRetrofitModule