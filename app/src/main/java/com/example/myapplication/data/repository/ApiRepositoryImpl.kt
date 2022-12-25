package com.example.myapplication.data.api.repository

import android.location.Location
import com.example.myapplication.Constants
import com.example.myapplication.Constants.language
import com.example.myapplication.data.api.ApiCurrent
import com.example.myapplication.data.api.ApiForecast
import com.example.myapplication.data.api.ModelApi
import com.example.myapplication.data.models.ModelApiCurrent
import com.example.myapplication.domain.repository.ApiRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt


class ApiRepositoryImpl(private val apiCurrent: ApiCurrent, private val apiForecast: ApiForecast) :
    ApiRepository {
    override var city: Location? = null

    override suspend fun getApiRezults(): ModelApiCurrent? {
        var q: ModelApiCurrent? = null
        val jobList = mutableListOf<Deferred<ModelApiCurrent>>()
        withContext(Dispatchers.IO) {

            jobList.add(async {

                apiCurrent.getData(
                    ((city?.latitude?.times(100))?.roundToInt()?.div(100.0)).toString(),
                    ((city?.longitude?.times(100))?.roundToInt()?.div(100.0)).toString(),
                    Constants.token,
                    language
                ).execute().body()!!
            })
            q = jobList.mapNotNull {
                it.await()
            }[0]
        }

        return q
    }

    override suspend fun getApiForecastRezults(): List<ModelApi> {
        var q = listOf<ModelApi>()
        val jobList = mutableListOf<Deferred<ModelApi>>()
        withContext(Dispatchers.IO) {
            jobList.add(async {
                apiForecast.getData(
                    ((city?.latitude?.times(100))?.roundToInt()?.div(100.0)).toString(),
                    ((city?.longitude?.times(100))?.roundToInt()?.div(100.0)).toString(),
                    Constants.token,
                    language
                ).execute().body()!!
            })
            q = jobList.mapNotNull {
                it.await()
            }

        }

        return q
    }
}
