package com.example.myapplication

import android.app.Application
import android.location.Location
import android.util.Log
import com.example.myapplication.dimodule.dataModule
import com.example.myapplication.dimodule.domainModule
import com.example.myapplication.dimodule.viewModelModule
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(dataModule,domainModule,viewModelModule))
        }

    }

}


