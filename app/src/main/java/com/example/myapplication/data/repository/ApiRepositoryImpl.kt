package com.example.myapplication.data.api.repository

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Constants
import com.example.myapplication.data.api.ApiCurrent
import com.example.myapplication.data.api.ApiForecast
import com.example.myapplication.data.api.ModelApi

import com.example.myapplication.data.models.Model

import com.example.myapplication.data.models.ModelApiCurrent
import com.example.myapplication.domain.repository.ApiRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt
import kotlin.math.roundToLong


class ApiRepositoryImpl(private val apiCurrent: ApiCurrent,private val apiForecast: ApiForecast) : ApiRepository {
override var city:Location?=null
set(value) {
    field = value

}

    override suspend fun getApiRezults(): ModelApiCurrent? {
        var q: ModelApiCurrent? = null
        val jobList = mutableListOf<Deferred<ModelApiCurrent>>()
        withContext(Dispatchers.IO) {

            jobList.add(async {
                println(city?.latitude.toString()+" LOCAT "+city?.longitude.toString())
                apiCurrent.getData(

                    ((city?.latitude?.times(100))?.roundToInt()?.div(100.0)).toString(), ((city?.longitude?.times(100))?.roundToInt()?.div(100.0)).toString(),Constants.token, "ru").execute().body()!!
            })
            q = jobList.mapNotNull {
                it.await()
            }[0]
        }

        return q
    }
    override suspend fun getApiForecastRezults(): List<ModelApi> {
        var q=listOf<ModelApi>()
        val jobList = mutableListOf<Deferred<ModelApi>>()
        withContext(Dispatchers.IO) {
            jobList.add(async {apiForecast.getData( ((city?.latitude?.times(100))?.roundToInt()?.div(100.0)).toString(), ((city?.longitude?.times(100))?.roundToInt()?.div(100.0)).toString(),Constants.token,"ru").execute().body()!!})
            q=jobList.mapNotNull {
                it.await()
            }

        }
       // println("Appppppiiiiiii")
        //println(q)
        return q
    }
}
//:List<ModelApi?>=list




        /*var q: ModelApiCurrent? = null
        apiCurrent.getData("penza", Constants.token, "ru").enqueue(
            object : Callback<ModelApiCurrent> {
                override fun onResponse(
                    call: Call<ModelApiCurrent>,
                    response: Response<ModelApiCurrent>
                ) {
                    q= response.body()
                    println("Wow")
                    println(q)


                }

                override fun onFailure(call: Call<ModelApiCurrent>, t: Throwable) {
                    println("no")
                }


            })
        println("Woow")
        println(q)
        return q
    }*/


/*
 var q:ModelApi?=null
        val jobList = mutableListOf<Deferred<ModelApi>>()
        withContext(Dispatchers.IO) {
            jobList.add(async {rickApi.getData("Penza",Constants.token,"ru").execute().body()!!})
            q=jobList.mapNotNull {
                   it.await()
                }[0]


        }


 */







//  api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=0c223951facc56292b4f3f96b2a12518
/*
override suspend fun getApiRezults(): List<Model> {
        val k = 42
        val jobList = mutableListOf<Deferred<Response<rezultsDto>>>()
        for (i in 1 until k) {
            withContext(Dispatchers.IO) {
                jobList.add(async { rickApi.getData(i.toString()).execute() })
            }
        }
        val listOfResults = jobList.mapNotNull {
            runCatching {
                it.await()
            }.getOrNull()
        }

        return listOfResults.let { responseList ->
            responseList.mapNotNull { it.body()?.ListItemDataDto?.toList() }
                .reduceRightOrNull { cur, acc ->
                    acc + cur
                }?.sortedBy { it.id } ?: emptyList()
        }
    }
 */
