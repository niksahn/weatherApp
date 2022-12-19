package com.example.myapplication

import android.app.Application
import com.example.myapplication.dimodule.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)

            modules(listOf(dataModule,domainModule,viewModelModule, mainAct))
        }

    }

}


