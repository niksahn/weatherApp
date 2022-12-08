package com.example.myapplication.domain.interactor

import com.example.myapplication.data.models.Model

interface Interactor {
    suspend fun setApiRezults(): Model?;
}