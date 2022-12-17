package com.example.myapplication.ui

import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.App
import com.example.myapplication.data.models.Model
import com.example.myapplication.data.models.modelForviewModel.ModelCurrent
import com.example.myapplication.domain.interactor.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.util.*


class ViewModel(private val interactor: Interactor)  : ViewModel(){
    var weather: MutableLiveData<ModelCurrent> = MutableLiveData()
    var forecast: MutableLiveData<List<Model>> = MutableLiveData()
    var ap= App()
    private var time: Long? = 0
    init {
        time = interactor.setTime()
        val t = time ?: 0.toLong()
      if (((t == 0.toLong()) || (Date().time - t > 60 * 100))){// впервые/ 10 минут прошли
            viewModelScope.launch(Dispatchers.IO) {
                supervisorScope {
                    try{
                    weather.postValue(interactor.setApiRezults())
                    interactor.InsertCurrentWeather()
                    forecast.postValue(interactor.setApiForecastRezults())
                    interactor.InsertForecast()
                    }
                    catch (e:Exception)
                    {
                        forecast.postValue(interactor.GetForecast())
                        weather.postValue(interactor.GetCurrentWeather())
                    }

                }
            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                supervisorScope {
                    forecast.postValue(interactor.GetForecast())
                    weather.postValue(interactor.GetCurrentWeather())
                }
            }


        }

    }
}

