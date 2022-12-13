package com.example.myapplication.data.repository

import com.example.myapplication.data.models.ModelApiCurrent
import com.example.myapplication.data.room.AppDatabase
import com.example.myapplication.domain.repository.DbRepository

class DbRepositoryImpl(private val db: AppDatabase) : DbRepository {
    override fun getCurWeather() = db.weatherDao.currentweather()

    override suspend fun insertCurWeatherFromApi(Api: ModelApiCurrent) {
        db.weatherDao.currentweatherInsert(Api.toModelCurrentEntity() )
    }
}