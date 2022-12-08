package com.example.myapplication.data.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
//import retrofit2.http.Query


interface RickApi {
    @GET("data/2.5/weather")
    fun getData(
        @Query("q") q: String?,
        @Query("appid") appid: String,
    ): Call<ModelApi>
}
//     api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=0c223951facc56292b4f3f96b2a12518
//https://api.openweathermap.org/data/3.0/onecall?lat=33.44&lon=-94.04&exclude=hourly,daily&appid={API key}
/*fun rickapi():RickApi{
    val baseurl = "https://rickandmortyapi.com"
    val retrofit = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(RickApi::class.java)}
*/