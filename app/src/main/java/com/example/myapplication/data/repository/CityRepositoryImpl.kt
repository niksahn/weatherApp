package com.example.myapplication.data.repository


import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.dimodule.LOCATION_PERMISSION_REQUEST_CODE

import com.example.myapplication.domain.repository.CityRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Tasks

class CityRepositoryImpl(private val cityTask: FusedLocationProviderClient):CityRepository {
    var loc: MutableLiveData<Location> ?=null
    val priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    val cancellationTokenSource = CancellationTokenSource()
    override var permissionReqCode:Int =0
        set(value) {
            field = value

        }
    override var permissionReqGranted:IntArray?=null
        set(value) {
            field = value

        }




    override fun getCity(): Location? {
       loc?.value= when(permissionReqCode){
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (permissionReqGranted?.get(0) == PackageManager.PERMISSION_GRANTED) {
                    var city = cityTask.getCurrentLocation(priority, cancellationTokenSource.token)
                        .addOnSuccessListener { location ->
                            Log.d("Location", "location is found: $location")
                            loc?.value?.latitude = location.latitude

                            loc?.value?.longitude = location.longitude
                        }
                        .addOnFailureListener { exception ->
                            Log.d("Location", "Oops location failed with exception: $exception")

                            loc?.value?.latitude = 0.0

                            loc?.value?.longitude = 0.0
                        }


                    //println(loc?.latitude.toString()+" LOCAT")
                    return Tasks.await(city)
                }
                else return null

            }
           else -> {return null}
       }
   return loc?.value }}









