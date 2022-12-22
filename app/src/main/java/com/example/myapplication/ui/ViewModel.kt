package com.example.myapplication.ui


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    var fragment:MutableLiveData<Int> = MutableLiveData()

    private var time: Long? = null
    init {

        time = interactor.setTime()
        val t = time

        println(t)
        //((t == null) || (Date().time - t > 600*100))|| permission==0
      if (((t == null) || (Date().time - t >300*1000))|| permission==0){// впервые/ 10 минут прошли
            viewModelScope.launch(Dispatchers.IO) {
                supervisorScope {
                    while (permission==0){}
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

