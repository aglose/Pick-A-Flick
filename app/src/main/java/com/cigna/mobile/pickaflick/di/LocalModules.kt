package com.cigna.mobile.pickaflick.di

import androidx.room.Room
import com.cigna.mobile.pickaflick.data.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, "database-name"
        ).build()
    }
}