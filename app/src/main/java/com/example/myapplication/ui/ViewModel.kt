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

    private var time: Long? = null

    init {

        time = interactor.setTime()
        val t = time
        viewModelScope.launch(Dispatchers.IO) {
            supervisorScope {
                if (((t == null) || (Date().time - t > 300 * 1000))) {// впервые/ 5 минут прошли

                    while (permission == 0) {
                    }
                    try {
                        interactor.getcity()
                        interactor.InsertCurrentWeather()
                        interactor.InsertForecast()
                    } catch (e: java.lang.Exception) {
                    }
                }
                forecast.postValue(interactor.GetForecast())
                weather.postValue(interactor.GetCurrentWeather())
            }
        }
    }
}

