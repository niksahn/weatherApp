package com.example.myapplication.data.models.roomEntity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.data.models.Model


@Entity(tableName = "ListForecastWeather")
data class ModelEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    var temp: Double? = null,
    var feelsLike: Double? = null,
    var humidity: Int? = null,
    var icon: String? = null,
    var dtTxt: String? = null

) {
    fun toList() = Model(null, temp, feelsLike, humidity, icon, dtTxt)
}