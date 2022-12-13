package com.example.myapplication.domain.interactor

import com.example.myapplication.data.models.Model
import com.example.myapplication.data.models.ModelApiCurrent
import com.example.myapplication.data.models.modelForviewModel.ModelCurrent

interface Interactor {
     suspend fun setApiRezults(): ModelCurrent?
     suspend fun setApiForecastRezults(): List<Model>?
     suspend fun InsertCurrentWeather()
     fun GetCurrentWeather(): ModelCurrent
     fun setTime(): Long?
     suspend fun InsertForecast()
     fun GetForecast(): List<Model>

}