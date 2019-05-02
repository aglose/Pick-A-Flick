package com.cigna.mobile.pickaflick

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.cigna.mobile.pickaflick.di.repositoryModule
import com.cigna.mobile.pickaflick.di.retrofitModules
import com.cigna.mobile.pickaflick.di.roomModule
import com.cigna.mobile.pickaflick.di.viewModelModules
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

        startKoin {
            androidContext(this@MyApplication)
            modules(viewModelModules +
                    roomModule +
                    retrofitModules +
                    repositoryModule)
        }

        AppCompatDelegate.setDefaultNightMode(
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }

}