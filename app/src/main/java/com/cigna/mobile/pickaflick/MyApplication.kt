package com.cigna.mobile.pickaflick

import android.app.Application
import com.cigna.mobile.movie.di.movieRepositoryModule
import com.cigna.mobile.pickaflick.di.movieRetrofitModule
import com.cigna.mobile.pickaflick.di.roomModule
import com.cigna.mobile.pickaflick.di.viewModelModules
import com.cigna.mobile.pickaflick.di.weatherRetrofitModule
import com.cigna.mobile.weather.di.weatherRepositoryModule
import leakcanary.LeakCanary
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to Perflib for heap analysis.
            // You should not init your app in this process.
            return
        }
        super.onCreate()

        val repositories = movieRepositoryModule + weatherRepositoryModule

        startKoin {
            androidContext(this@MyApplication)
            modules(repositories
                    + viewModelModules
                    + roomModule
                    + weatherRetrofitModule
                    + movieRetrofitModule)
        }
    }

}