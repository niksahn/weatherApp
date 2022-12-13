package com.example.myapplication.data.api


import com.example.myapplication.data.models.*
import com.example.myapplication.data.models.roomEntity.ModelEntity
import com.google.gson.annotations.SerializedName

data class ModelApi (

   @SerializedName("cod"     ) var cod     : String?         = null,
   @SerializedName("message" ) var message : Int?            = null,
   @SerializedName("cnt"     ) var cnt     : Int?            = null,
   @SerializedName("list"    ) var list    : ArrayList<ListApi> = arrayListOf(),
   @SerializedName("city"    ) var city    : CityApi?           = CityApi()

){

}
data class MainApi (

   @SerializedName("temp"       ) var temp      : Double? = null,
   @SerializedName("feels_like" ) var feelsLike : Double? = null,
   @SerializedName("temp_min"   ) var tempMin   : Double? = null,
   @SerializedName("temp_max"   ) var tempMax   : Double? = null,
   @SerializedName("pressure"   ) var pressure  : Int?    = null,
   @SerializedName("sea_level"  ) var seaLevel  : Int?    = null,
   @SerializedName("grnd_level" ) var grndLevel : Int?    = null,
   @SerializedName("humidity"   ) var humidity  : Int?    = null,
   @SerializedName("temp_kf"    ) var tempKf    : Double? = null

)
data class WeatherApi (

   @SerializedName("id"          ) var id          : Int?    = null,
   @SerializedName("main"        ) var main        : String? = null,
   @SerializedName("description" ) var description : String? = null,
   @SerializedName("icon"        ) var icon        : String? = null

)
data class Clouds (

   @SerializedName("all" ) var all : Int? = null

)

data class WindApi (

   @SerializedName("speed" ) var speed : Double? = null,
   @SerializedName("deg"   ) var deg   : Int?    = null,
   @SerializedName("gust"  ) var gust  : Double? = null

)

data class Sys (

   @SerializedName("pod" ) var pod : String? = null

)

data class ListApi (

   @SerializedName("dt"         ) var dt         : Int?               = null,
   @SerializedName("main"       ) var main       : MainApi?              = MainApi(),
   @SerializedName("weather"    ) var weather    : ArrayList<WeatherApi> = arrayListOf(),
   @SerializedName("clouds"     ) var clouds     : Clouds?            = Clouds(),
   @SerializedName("wind"       ) var wind       : WindApi?              = WindApi(),
   @SerializedName("visibility" ) var visibility : Int?               = null,
   @SerializedName("pop"        ) var pop        : Double?               = null,
   @SerializedName("sys"        ) var sys        : Sys?               = Sys(),
   @SerializedName("dt_txt"     ) var dtTxt      : String?            = null

){
   fun toList()=Model(null,main?.temp,main?.feelsLike,main?.humidity,weather[0]?.icon,dtTxt)
   fun toListEntity()= ModelEntity(null,main?.temp,main?.feelsLike,main?.humidity,weather[0]?.icon,dtTxt)
}

data class Coord (

   @SerializedName("lat" ) var lat : Double? = null,
   @SerializedName("lon" ) var lon : Double? = null

)

data class CityApi (

   @SerializedName("id"         ) var id         : Int?    = null,
   @SerializedName("name"       ) var name       : String? = null,
   @SerializedName("coord"      ) var coord      : Coord?  = Coord(),
   @SerializedName("country"    ) var country    : String? = null,
   @SerializedName("population" ) var population : Int?    = null,
   @SerializedName("timezone"   ) var timezone   : Int?    = null,
   @SerializedName("sunrise"    ) var sunrise    : Int?    = null,
   @SerializedName("sunset"     ) var sunset     : Int?    = null

)































/*
data class ModelApi (

   @SerializedName("coord"      ) var coord      : CoordApi?             = CoordApi(),
   @SerializedName("weather"    ) var weather    : ArrayList<Weather> = arrayListOf(),
   @SerializedName("base"       ) var base       : String?            = null,
   @SerializedName("main"       ) var main       : MainApi?              = MainApi(),
   @SerializedName("visibility" ) var visibility : Int?               = null,
   @SerializedName("wind"       ) var wind       : Wind?              = Wind(),
   @SerializedName("clouds"     ) var clouds     : Clouds?            = Clouds(),
   @SerializedName("dt"         ) var dt         : Int?               = null,
   @SerializedName("sys"        ) var sys        : Sys?               = Sys(),
   @SerializedName("timezone"   ) var timezone   : Int?               = null,
   @SerializedName("id"         ) var id         : Int?               = null,
   @SerializedName("name"       ) var name       : String?            = null,
   @SerializedName("cod"        ) var cod        : Int?               = null

){
   fun toModel()= Model(coord?.toCoord(),weather,base,main?.toMain(),visibility,null,null,dt,null,timezone,id,name,cod)
}
data class CoordApi (
   @SerializedName("lon" ) var lon : Double? = null,
   @SerializedName("lat" ) var lat : Double? = null

){fun toCoord()= Coord(lon,lat) }
data class Weather (

   @SerializedName("id"          ) var id          : Int?    = null,
   @SerializedName("main"        ) var main        : String? = null,
   @SerializedName("description" ) var description : String? = null,
   @SerializedName("icon"        ) var icon        : String? = null

)

data class MainApi (

   @SerializedName("temp"       ) var temp      : Double? = null,
   @SerializedName("feels_like" ) var feelsLike : Double? = null,
   @SerializedName("temp_min"   ) var tempMin   : Double? = null,
   @SerializedName("temp_max"   ) var tempMax   : Double? = null,
   @SerializedName("pressure"   ) var pressure  : Int?    = null,
   @SerializedName("humidity"   ) var humidity  : Int?    = null

){fun toMain()= Main(temp,feelsLike,tempMin,tempMax,pressure,humidity) }

data class Wind (

   @SerializedName("speed" ) var speed : Double? = null,
   @SerializedName("deg"   ) var deg   : Int?    = null,
   @SerializedName("gust"  ) var gust  : Double? = null

)


data class Clouds (

   @SerializedName("all" ) var all : Int? = null

)

data class Sys (

   @SerializedName("type"    ) var type    : Int?    = null,
   @SerializedName("id"      ) var id      : Int?    = null,
   @SerializedName("country" ) var country : String? = null,
   @SerializedName("sunrise" ) var sunrise : Int?    = null,
   @SerializedName("sunset"  ) var sunset  : Int?    = null

)*/