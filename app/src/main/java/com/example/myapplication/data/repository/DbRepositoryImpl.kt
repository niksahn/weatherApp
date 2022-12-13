package com.example.myapplication.data.repository

import com.example.myapplication.data.api.ModelApi
import com.example.myapplication.data.models.ModelApiCurrent
import com.example.myapplication.data.room.AppDatabase
import com.example.myapplication.domain.repository.DbRepository

class DbRepositoryImpl(private val db: AppDatabase) : DbRepository {
    override fun getCurWeather() = db.weatherDao.currentweatherGet()

    override suspend fun insertCurWeatherFromApi(Api: ModelApiCurrent) {
        db.weatherDao.currentweatherInsert(Api.toModelCurrentEntity() )
    }
    override fun GetForecast() = db.weatherDao.forecastGet()

    override suspend fun insertForecastFromApi(Apilist:  ModelApi) {
        db.weatherDao.forecastDel()
        db.weatherDao.forecastInsert(Apilist.list.map { it.toListEntity() } )
    }
}