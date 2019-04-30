package com.cigna.mobile.pickaflick.di

import com.cigna.mobile.pickaflick.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val movieRetrofitModule = module {
    single { OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build() }
    single { Retrofit.Builder()
        .client(get())
        .baseUrl("https://api.themoviedb.org/3/")
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build() }
}

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

val weatherRetrofitModule = module {
    single { OkHttpClient().newBuilder().build() }
    single { Retrofit.Builder()
        .client(get())
        .baseUrl("https://api.weather.gov/")
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build() }
}