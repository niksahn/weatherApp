package com.example.myapplication.data.api

import com.example.myapplication.data.models.ModelApiCurrent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCurrent {
    @GET("data/2.5/weather")
    fun getData(
        @Query("lat") lat: String?,
        @Query("lon") lon: String?,
        @Query("appid") appid: String,
        @Query("lang") lang: String,
    ): Call<ModelApiCurrent>
}