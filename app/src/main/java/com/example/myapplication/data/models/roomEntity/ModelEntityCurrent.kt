package com.example.myapplication.data.models.roomEntity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myapplication.data.models.modelForviewModel.ModelCurrent

@Entity(tableName = "CurrentWeather")
data class ModelCurrentEntity(
    @PrimaryKey var id: Int = 0,
//var coord      : CoordApiCurrent?             = CoordApiCurrent(),
    //var weather    : ArrayList<WeatherApiCurrent> = arrayListOf(),
    var base: String? = null,
    //var main       : MainApiCurrent?              = MainApiCurrent(),
    var visibility: Int? = null,
    //var wind       : WindApiCurrent?              = WindApiCurrent(),
    //var clouds     : CloudsApiCurrent?            = CloudsApiCurrent(),
    var dt: Int? = null,
    //var sys        : SysApiCurrent?               = SysApiCurrent(),
    var timezone: Int? = null,
    var name: String? = null,
    var cod: Int? = null,


    var icon: String? = null,
    var description: String? = null,

    var temp: Double? = null,

    var pressure: Int? = null,
    var humidity: Int? = null,

    var speed: Double? = null,


    ) {
    fun toModelCurrent() = ModelCurrent(
        0,
        base,
        visibility,
        dt,
        timezone,
        name,
        cod,
        icon,
        description,
        temp,
        pressure,
        humidity,
        speed
    )
}



