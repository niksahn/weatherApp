package com.example.myapplication.ui

import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.App
import com.example.myapplication.data.models.Model
import com.example.myapplication.data.models.modelForviewModel.ModelCurrent
import com.example.myapplication.dimodule.LOCATION_PERMISSION_REQUEST_CODE
import com.example.myapplication.domain.interactor.Interactor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.util.*


class ViewModel(private val interactor: Interactor)  : ViewModel(){
    var weather: MutableLiveData<ModelCurrent> = MutableLiveData()
    var forecast: MutableLiveData<List<Model>> = MutableLiveData()
    var permissionReqCode: MutableLiveData<Int> =MutableLiveData()
    var permissionReqGranted: MutableLiveData<IntArray> =MutableLiveData()

    private var time: Long? = null
    init {

        time = interactor.setTime()
        val t = time

        println(t)
      if (((t == null) || (Date().time - t > 600*100))){// впервые/ 10 минут прошли
            viewModelScope.launch(Dispatchers.IO) {
                supervisorScope {
                    interactor.getcity()
                    weather.postValue(interactor.setApiRezults())
                    interactor.InsertCurrentWeather()
                    forecast.postValue(interactor.setApiForecastRezults())
                    interactor.InsertForecast()
            }
        }} else {
            viewModelScope.launch(Dispatchers.IO) {
                supervisorScope {

                    forecast.postValue(interactor.GetForecast())
                    weather.postValue(interactor.GetCurrentWeather())
                }
            }


        }

    }
}

