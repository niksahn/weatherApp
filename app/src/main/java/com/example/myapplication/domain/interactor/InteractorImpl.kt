package com.example.myapplication.domain.interactor

import com.example.myapplication.Constants
import com.example.myapplication.data.models.Model
import com.example.myapplication.data.models.ModelApiCurrent
import com.example.myapplication.data.models.modelForviewModel.ModelCurrent
import com.example.myapplication.domain.repository.ApiRepository
import com.example.myapplication.domain.repository.DbRepository
import com.example.myapplication.domain.repository.SharedPreferencesRepository
import java.util.*

class InteractorImpl(
    private val ApiRepository: ApiRepository,
    private val DbRepository: DbRepository,
    private val SharedPreferencesRepository: SharedPreferencesRepository

) : Interactor {
    override fun setTime(): Long? {
        var time: Long? = 0
        val editor = SharedPreferencesRepository.timeEditor()
        if (!SharedPreferencesRepository.created()) {//запустили впервые
            editor.putString(Constants.APP_PREFERENCES_TIME, Date().time.toString())
            editor.apply()
        } else {//обновили время
            time = SharedPreferencesRepository.timeSetting()
            editor.putString(Constants.APP_PREFERENCES_TIME, Date().time.toString())
            editor.apply()

        }
        return time}

     override suspend fun setApiRezults(): ModelCurrent? {
        val joblist = ApiRepository.getApiRezults()

        return joblist?.toModelCurrent()
    }

    override suspend fun setApiForecastRezults(): List<Model>? {
        val joblist = ApiRepository.getApiForecastRezults()

        return joblist?.map {it.toModel()  }

    }
    override suspend fun InsertCurrentWeather() {
        ApiRepository.getApiRezults()?.let { DbRepository.insertCurWeatherFromApi(it) }
    }

    override fun GeturrentWeather(): ModelCurrent {
        return DbRepository.getCurWeather().toModelCurrent()
    }

}
