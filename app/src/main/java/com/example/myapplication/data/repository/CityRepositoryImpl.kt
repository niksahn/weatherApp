package com.example.myapplication.data.repository


import android.location.Location
import android.util.Log

import com.example.myapplication.domain.repository.CityRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationTokenSource

class CityRepositoryImpl(private val cityTask: FusedLocationProviderClient):CityRepository {
    var loc: Location?=null
    val priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    val cancellationTokenSource = CancellationTokenSource()




   override fun getCity(

    ): Location? {
          println("NOOOOOOOOOOOOOOOPEERM")
                var city=cityTask.getCurrentLocation(priority, cancellationTokenSource.token)
                    .addOnSuccessListener { location ->
                        Log.d("Location", "location is found: $location")
                        loc?.latitude=location.latitude
                        println(location.latitude.toString()+" "+location.longitude+" RRRRRRRRRRRRRRRRRRRRRRRRRRR")
                        loc?.longitude=location.longitude
                    }
                    .addOnFailureListener { exception ->
                        Log.d("Location", "Oops location failed with exception: $exception")
                        println("NoLoc")
                        loc?.latitude=10.00
                        loc?.longitude=10.00
                    }



        println(loc?.latitude.toString()+" LOCAT")
        return loc?:null
    }


}




