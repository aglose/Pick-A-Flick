package com.cigna.mobile.pickaflick.di

import com.cigna.mobile.pickaflick.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel { MainViewModel(get(), get()) }
}