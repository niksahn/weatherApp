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


class ViewModel(private val interactor: Interactor) : ViewModel() {
    var weather: MutableLiveData<ModelCurrent> = MutableLiveData()
    var forecast: MutableLiveData<List<Model>> = MutableLiveData()
    var fragment: MutableLiveData<Int> = MutableLiveData()

     var time:MutableLiveData<Long?> = MutableLiveData()

    init {

        time.postValue(interactor.getTime())
        while (permission == 0) {}
        viewModelScope.launch(Dispatchers.IO) {
            supervisorScope {
                    try {
                        interactor.getcity()
                        interactor.InsertCurrentWeather()
                        interactor.InsertForecast()
                        time.postValue(Date().time)
                        interactor.setTime()
                    } catch (e: java.lang.Exception) {}
                forecast.postValue(interactor.GetForecast())
                weather.postValue(interactor.GetCurrentWeather())
            }
        }
    }
}

