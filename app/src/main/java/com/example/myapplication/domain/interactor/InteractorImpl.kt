package com.example.myapplication.domain.interactor

import com.example.myapplication.data.models.Model
import com.example.myapplication.data.models.ModelApiCurrent
import com.example.myapplication.domain.repository.ApiRepository

class InteractorImpl(
    private val ApiRepository: ApiRepository,

) : Interactor {
     override suspend fun setApiRezults(): ModelApiCurrent? {
        val joblist = ApiRepository.getApiRezults()

        return joblist
    }

    override suspend fun setApiForecastRezults(): List<Model>? {
        val joblist = ApiRepository.getApiForecastRezults()

        return joblist?.map {it.toModel()  }

    }

}
