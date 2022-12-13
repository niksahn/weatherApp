package com.example.myapplication.domain.repository

import com.example.myapplication.data.models.ModelApiCurrent

import com.example.myapplication.data.models.roomEntity.ModelCurrentEntity

interface DbRepository {
     fun getCurWeather() :ModelCurrentEntity

    suspend fun insertCurWeatherFromApi(Api: ModelApiCurrent)
}