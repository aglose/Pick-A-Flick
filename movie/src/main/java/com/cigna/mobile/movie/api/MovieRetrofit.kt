package com.cigna.mobile.movie.api

import com.cigna.mobile.movie.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit

val movieRetrofitModule = module {
    single { getRetrofit().create(TmdbApi::class.java) }
}

fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(getMovieHttpClient())
        .baseUrl("https://api.themoviedb.org/3/")
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

private fun getMovieHttpClient() =  OkHttpClient().newBuilder()
    .addInterceptor(authInterceptor)
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