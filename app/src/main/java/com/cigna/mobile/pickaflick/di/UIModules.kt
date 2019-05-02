package com.cigna.mobile.pickaflick.di

import com.cigna.mobile.movie.ui.MovieViewModel
import com.cigna.mobile.pickaflick.ui.MainViewModel
import com.cigna.mobile.weather.ui.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { MainViewModel(get(), get()) }
    viewModel { WeatherViewModel(get()) }
    viewModel { MovieViewModel(get()) }
}