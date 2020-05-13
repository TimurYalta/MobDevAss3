package com.example.mobdevass3

import android.app.Application
import com.example.mobdevass3.data.prefs.Prefs
import com.github.ajalt.timberkt.Timber
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import timber.log.Timber.DebugTree
import org.koin.core.context.startKoin


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
        initKoin()
        Prefs.createEncryptedPreferences(context = this)
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                listOf(
                    createAppModule()
                )
            )
        }
    }


}