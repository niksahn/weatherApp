package com.example.myapplication.domain.repository

import com.example.myapplication.data.api.ModelApi
import com.example.myapplication.data.models.ModelApiCurrent

import com.example.myapplication.data.models.roomEntity.ModelCurrentEntity
import com.example.myapplication.data.models.roomEntity.ModelEntity

interface DbRepository {
    fun getCurWeather(): ModelCurrentEntity
    suspend fun insertCurWeatherFromApi(Api: ModelApiCurrent)
    fun GetForecast(): List<ModelEntity>
    suspend fun insertForecastFromApi(Apilist: ModelApi)
}