package com.hypersoft.roomdb

import android.app.Application
import com.hypersoft.roomdb.di.modulesList
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
    private fun initKoin() {
        startKoin {
            androidContext(this@MainApplication)
            modules(modulesList)
        }
    }
}