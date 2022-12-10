package com.example.myapplication.domain.repository

import com.example.myapplication.data.api.ModelApi
import com.example.myapplication.data.models.ModelApiCurrent

interface ApiRepository {
    suspend fun getApiRezults(): ModelApiCurrent?
}