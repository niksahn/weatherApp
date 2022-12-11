package com.example.myapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.models.Model
import com.example.myapplication.data.models.ModelApiCurrent
import com.example.myapplication.domain.interactor.Interactor
import com.example.myapplication.ui.Fragm.putingInPlaceHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.util.*

class ViewModel(private val interactor: Interactor)  : ViewModel(){
    var weather: MutableLiveData<ModelApiCurrent> = MutableLiveData()
    var forecast: MutableLiveData<List<Model>> = MutableLiveData()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            supervisorScope {
                weather.postValue(interactor.setApiRezults())
                forecast.postValue(interactor.setApiForecastRezults())

            }

    }

    }}

