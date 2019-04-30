package com.cigna.mobile.movie.di

import com.cigna.mobile.movie.data.MovieRepository
import com.cigna.mobile.movie.data.MovieRepositoryImpl
import org.koin.dsl.module

val movieRepositoryModule = module {
    single<MovieRepository> { MovieRepositoryImpl(get(), get()) }
}