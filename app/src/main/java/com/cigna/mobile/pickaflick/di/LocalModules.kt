package com.cigna.mobile.pickaflick.di

import androidx.room.Room
import com.cigna.mobile.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java, "database-name"
        ).build()
    }

   single { get<AppDatabase>().weatherDao() }
   single { get<AppDatabase>().movieDao() }
}