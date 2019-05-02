package com.cigna.mobile.movie.di

import com.cigna.mobile.movie.api.TmdbApi
import com.cigna.mobile.shared.BuildConfig
import com.cigna.mobile.shared.getLoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val movieRetrofitModule = module {
    single { getMovieRetrofit().create(TmdbApi::class.java) }
}

private fun getMovieRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(getMovieHttpClient())
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

private fun getMovieHttpClient() =  OkHttpClient.Builder()
    .addInterceptor(authInterceptor)
    .addInterceptor(getLoggingInterceptor())
    .build()

private val authInterceptor = Interceptor {chain->
    val newUrl = chain.request().url()
        .newBuilder()
        .addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)
        .build()

    val newRequest = chain.request()
        .newBuilder()
        .url(newUrl)
        .build()

    chain.proceed(newRequest)
}