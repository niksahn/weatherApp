package com.example.myapplication.data.models

import com.example.myapplication.data.models.modelForviewModel.*
import com.example.myapplication.data.models.roomEntity.ModelCurrentEntity

import com.google.gson.annotations.SerializedName
data class ModelApiCurrent (

    @SerializedName("coord"      ) var coord      : CoordApiCurrent?             = CoordApiCurrent(),
    @SerializedName("weather"    ) var weather    : ArrayList<WeatherApiCurrent> = arrayListOf(),
    @SerializedName("base"       ) var base       : String?            = null,
    @SerializedName("main"       ) var main       : MainApiCurrent?              = MainApiCurrent(),
    @SerializedName("visibility" ) var visibility : Int?               = null,
    @SerializedName("wind"       ) var wind       : WindApiCurrent?              = WindApiCurrent(),
    @SerializedName("clouds"     ) var clouds     : CloudsApiCurrent?            = CloudsApiCurrent(),
    @SerializedName("dt"         ) var dt         : Int?               = null,
    @SerializedName("sys"        ) var sys        : SysApiCurrent?               = SysApiCurrent(),
    @SerializedName("timezone"   ) var timezone   : Int?               = null,
    @SerializedName("id"         ) var id         : Int?               = null,
    @SerializedName("name"       ) var name       : String?            = null,
    @SerializedName("cod"        ) var cod        : Int?               = null

){
    fun toModelCurrent()=ModelCurrent(0,base,visibility,dt,timezone,name,cod,weather[0].icon,weather[0].description,main?.temp,main?.pressure,main?.humidity,wind?.speed)
    fun toModelCurrentEntity()=ModelCurrentEntity(0,base,visibility,dt,timezone,name,cod,weather[0].icon,weather[0].description,main?.temp,main?.pressure,main?.humidity,wind?.speed)
}
data class CoordApiCurrent (

    @SerializedName("lon" ) var lon : Double? = null,
    @SerializedName("lat" ) var lat : Double? = null

)
data class WeatherApiCurrent (

    @SerializedName("id"          ) var id          : Int?    = null,
    @SerializedName("main"        ) var main        : String? = null,
    @SerializedName("description" ) var description : String? = null,
    @SerializedName("icon"        ) var icon        : String? = null

)
data class MainApiCurrent (

    @SerializedName("temp"       ) var temp      : Double? = null,
    @SerializedName("feels_like" ) var feelsLike : Double? = null,
    @SerializedName("temp_min"   ) var tempMin   : Double? = null,
    @SerializedName("temp_max"   ) var tempMax   : Double? = null,
    @SerializedName("pressure"   ) var pressure  : Int?    = null,
    @SerializedName("humidity"   ) var humidity  : Int?    = null

)

data class WindApiCurrent (

    @SerializedName("speed" ) var speed : Double? = null,
    @SerializedName("deg"   ) var deg   : Int?    = null

)
data class CloudsApiCurrent (

    @SerializedName("all" ) var all : Int? = null

)
data class SysApiCurrent (

    @SerializedName("type"    ) var type    : Int?    = null,
    @SerializedName("id"      ) var id      : Int?    = null,
    @SerializedName("country" ) var country : String? = null,
    @SerializedName("sunrise" ) var sunrise : Int?    = null,
    @SerializedName("sunset"  ) var sunset  : Int?    = null

)