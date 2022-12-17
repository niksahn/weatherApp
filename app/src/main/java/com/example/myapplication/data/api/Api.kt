package com.example.myapplication.data.api
import retrofit2.Call
import retrofit2.http.*
interface ApiForecast {
    @GET("data/2.5/forecast")
    fun getData(
        @Query("q") lat: String?,

        @Query("appid") appid: String,
        @Query("lang") lang: String,
    ): Call<ModelApi>
}
