package com.example.myapplication.dimodule
import android.content.ContentValues.TAG
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.room.Room
import org.koin.dsl.module
import com.example.myapplication.Constants
import com.example.myapplication.data.api.ApiCurrent
import com.example.myapplication.data.api.ApiForecast

import com.example.myapplication.data.api.repository.ApiRepositoryImpl
import com.example.myapplication.data.repository.DbRepositoryImpl
import com.example.myapplication.data.repository.SharedPreferencesRepositoryImpl
import com.example.myapplication.data.room.AppDatabase
import com.example.myapplication.domain.interactor.Interactor
import com.example.myapplication.domain.interactor.InteractorImpl
import com.example.myapplication.domain.repository.ApiRepository
import com.example.myapplication.domain.repository.DbRepository
import com.example.myapplication.domain.repository.SharedPreferencesRepository
import com.example.myapplication.ui.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<ApiRepository> { ApiRepositoryImpl(get(),get(),get()) }
    single<SharedPreferencesRepository> { SharedPreferencesRepositoryImpl(get()) }
    single<DbRepository> { DbRepositoryImpl(get()) }
    single {
        androidContext().getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiForecast::class.java)

    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiCurrent::class.java)

    }
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "weather.db"
        ).build()
    }
    single{

        LocationServices.getFusedLocationProviderClient( androidContext()).lastLocation.addOnSuccessListener {
                location : Location? ->
            if (location != null) {
                Log.d(TAG, "Location returned.");
                // write code here to to make the API call to the weather service and update the UI
                // this code here is running on the Main UI thread as far as I understand
            } else {
                Log.d(TAG, "Null location returned.");
            }
        }.result

    }


}
val domainModule = module {
    factory<Interactor> { InteractorImpl(get(),get(),get()) }

}
val viewModelModule = module {
    viewModel {
        ViewModel(get())
    }
}
