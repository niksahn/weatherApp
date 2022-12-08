package com.example.myapplication.data.api




import com.example.myapplication.data.models.Coord
import com.example.myapplication.data.models.Main
import com.example.myapplication.data.models.Model
import com.google.gson.annotations.SerializedName


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

)