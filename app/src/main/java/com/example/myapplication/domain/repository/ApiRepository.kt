package com.example.myapplication.domain.repository

import com.example.myapplication.data.api.ModelApi

interface ApiRepository {
    suspend fun getApiRezults(): ModelApi?
}