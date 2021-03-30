package com.casatamplarului.ctscanner

import android.app.Application
import com.casatamplarului.ctscanner.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class CTScannerApp : Application() {
    companion object {
        lateinit var instance: CTScannerApp
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidContext(this@CTScannerApp)
            androidLogger(Level.ERROR)

            modules(appModule)
        }
    }
}