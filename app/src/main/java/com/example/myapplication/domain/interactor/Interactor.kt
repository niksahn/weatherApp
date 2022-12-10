package com.example.myapplication.domain.interactor

import com.example.myapplication.data.models.ModelApiCurrent

interface Interactor {
     suspend fun setApiRezults(): ModelApiCurrent?;
}