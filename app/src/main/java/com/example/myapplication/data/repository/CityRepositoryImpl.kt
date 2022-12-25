package com.example.myapplication.data.repository


import android.content.Context
import android.location.Location
import android.util.Log
import com.example.myapplication.domain.repository.CityRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Tasks

class CityRepositoryImpl(
    private val cityTask: FusedLocationProviderClient,
    private val cont: Context
) : CityRepository {

    val priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
    val cancellationTokenSource = CancellationTokenSource()


    override fun getCity(): Location? {

        var city = cityTask.getCurrentLocation(priority, cancellationTokenSource.token)
            .addOnSuccessListener { location ->
                Log.d("Location", "location is found: $location")

            }
            .addOnFailureListener { exception ->
                Log.d("Location", "Oops location failed with exception: $exception")


            }





        return Tasks.await(city)
    }
}










