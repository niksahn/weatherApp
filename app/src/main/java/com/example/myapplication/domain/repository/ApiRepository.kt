package com.example.myapplication.domain.repository

import android.location.Location
import com.example.myapplication.data.api.ModelApi
import com.example.myapplication.data.models.ModelApiCurrent

interface ApiRepository {
    var city: Location?

    suspend fun getApiRezults(): ModelApiCurrent?
    suspend fun getApiForecastRezults(): List<ModelApi>
}