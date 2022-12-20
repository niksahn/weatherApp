package com.example.myapplication.data.repository


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.dimodule.LOCATION_PERMISSION_REQUEST_CODE

import com.example.myapplication.domain.repository.CityRepository
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.ui.permission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Tasks

class CityRepositoryImpl(private val cityTask: FusedLocationProviderClient,private val cont: Context):CityRepository {
    var loc: Location ?=null
    val priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    val cancellationTokenSource = CancellationTokenSource()




    override fun getCity(): Location? {

                       var city = cityTask.getCurrentLocation(priority, cancellationTokenSource.token)
                           .addOnSuccessListener { location ->
                               Log.d("Location", "location is found: $location")

                           }
                           .addOnFailureListener { exception ->
                               Log.d("Location", "Oops location failed with exception: $exception")


                           }     //println(loc?.latitude.toString()+" LOCAT")





        return Tasks.await(city)
    }
}










