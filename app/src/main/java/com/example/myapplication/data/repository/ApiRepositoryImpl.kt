package com.example.myapplication.data.api.repository

import com.example.myapplication.Constants
import com.example.myapplication.data.api.ModelApi
import com.example.myapplication.data.api.RickApi
import com.example.myapplication.domain.repository.ApiRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepositoryImpl(private val rickApi: RickApi) : ApiRepository {
    override suspend fun getApiRezults():  ModelApi? {
        var q:ModelApi?=null
    rickApi.getData("penza",Constants.token).enqueue(
    object : Callback<ModelApi> {
        override fun onResponse(call: Call<ModelApi>, response: Response<ModelApi>){
            q= response.body()
        }
        override fun onFailure(call: Call<ModelApi>, t: Throwable) {
        }

    })
        return q

}
}
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