package com.example.myapplication.data.api.repository

import androidx.lifecycle.MutableLiveData
import com.example.myapplication.Constants
import com.example.myapplication.data.api.ApiCurrent
import com.example.myapplication.data.api.ApiForecast
import com.example.myapplication.data.api.ModelApi

import com.example.myapplication.data.models.Model

import com.example.myapplication.data.models.ModelApiCurrent
import com.example.myapplication.domain.repository.ApiRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext


class ApiRepositoryImpl(private val apiCurrent: ApiCurrent,private val apiForecast: ApiForecast) : ApiRepository {
    override suspend fun getApiRezults(): ModelApiCurrent? {
        var q: ModelApiCurrent? = null
        val jobList = mutableListOf<Deferred<ModelApiCurrent>>()
        withContext(Dispatchers.IO) {
            jobList.add(async {
                apiCurrent.getData("Пенза", Constants.token, "ru").execute().body()!!
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
            jobList.add(async {apiForecast.getData("Пенза",Constants.token,"ru").execute().body()!!})
            q=jobList.mapNotNull {
                it.await()
            }

        }
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
