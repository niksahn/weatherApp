package com.example.myapplication.domain.interactor

import com.example.myapplication.data.models.Model
import com.example.myapplication.domain.repository.ApiRepository

class InteractorImpl(
    private val ApiRepository: ApiRepository,
) : Interactor {
    override suspend fun setApiRezults(): Model? {
        val joblist = ApiRepository.getApiRezults()
        return joblist?.toModel()
    }
}
