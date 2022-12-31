package com.example.myapplication.domain.interactor

import com.example.myapplication.Constants
import com.example.myapplication.data.models.Model
import com.example.myapplication.data.models.modelForviewModel.ModelCurrent
import com.example.myapplication.domain.repository.ApiRepository
import com.example.myapplication.domain.repository.CityRepository
import com.example.myapplication.domain.repository.DbRepository
import com.example.myapplication.domain.repository.SharedPreferencesRepository
import java.util.*

class InteractorImpl(
    private val ApiRepository: ApiRepository,
    private val DbRepository: DbRepository,
    private val SharedPreferencesRepository: SharedPreferencesRepository,
    private val CityRepository: CityRepository

) : Interactor {
    override fun getcity() {
        ApiRepository.city = CityRepository.getCity()

    }

    override fun setTime(): Long? {
        var time: Long? = null
        val editor = SharedPreferencesRepository.timeEditor()
        editor.putString(Constants.APP_PREFERENCES_TIME, Date().time.toString())
        editor.apply()
        return null
    }
    override fun getTime():Long?{
        if (SharedPreferencesRepository.created()) {
       return SharedPreferencesRepository.timeSetting()}
        else return null
    }
    override suspend fun setApiRezults(): ModelCurrent? {
        val joblist = ApiRepository.getApiRezults()

        return joblist?.toModelCurrent()
    }

    override suspend fun setApiForecastRezults(): List<Model>? {
        val joblist = ApiRepository.getApiForecastRezults()
        //  println("API")
        println(joblist)
        return joblist[0].list.map { it.toList() }

    }

    override suspend fun InsertCurrentWeather() {
        ApiRepository.getApiRezults()?.let { DbRepository.insertCurWeatherFromApi(it) }
    }

    override fun GetCurrentWeather(): ModelCurrent {
        return DbRepository.getCurWeather().toModelCurrent()
    }

    override suspend fun InsertForecast() {
        DbRepository.insertForecastFromApi(ApiRepository.getApiForecastRezults()[0])
    }

    override fun GetForecast(): List<Model> {
        //println("BBBBBBBBBDDDDDDDDDD")
        // println(DbRepository.GetForecast().map { it.toList() })
        return DbRepository.GetForecast().map { it.toList() }
    }
}
