package com.example.myapplication.data.models




data class Model(
    var id: Int? = null,
    var temp: Double? = null,
    var feelsLike: Double? = null,
    var humidity: Int? = null,
    var icon: String? = null,
    var dtTxt: String? = null

)

/*data class Model(

    var cod: String? = null,
    var message: Int? = null,
    var cnt: Int? = null,
    var list: ArrayList<List> = arrayListOf(),
    var city: City? = City()

)

data class Main(

    var temp: Double? = null,
    var feelsLike: Double? = null,
    var tempMin: Double? = null,
    var tempMax: Double? = null,
    var pressure: Int? = null,
    var seaLevel: Int? = null,
    var grndLevel: Int? = null,
    var humidity: Int? = null,
    var tempKf: Double? = null

)

data class Weather(

    var id: Int? = null,
    var main: String? = null,
    var description: String? = null,
    var icon: String? = null

)

data class Clouds(
    var all: Int? = null

)

data class Wind(

    var speed: Double? = null,
    var deg: Int? = null,
    var gust: Double? = null

)

data class Sys(

    var pod: String? = null

)

data class List(

    var dt: Int? = null,
    var main: Main? = Main(),
    var weather: ArrayList<Weather> = arrayListOf(),
    var clouds: Clouds? = Clouds(),
    var wind: Wind? = Wind(),
    var visibility: Int? = null,
    var pop: Double? = null,
    var sys: Sys? = Sys(),
    var dtTxt: String? = null

)

data class Coord(

    var lat: Double? = null,
    var lon: Double? = null

)

data class City(

    var id: Int? = null,
    var name: String? = null,
    var coord: Coord? = Coord(),
    var country: String? = null,
    var population: Int? = null,
    var timezone: Int? = null,
    var sunrise: Int? = null,
    var sunset: Int? = null

)


/*data class Model(

    var coord: Coord? = Coord(),
    var weather: ArrayList<Weather> = arrayListOf(),
    var base: String?            = null,
    var main: Main? = Main(),
    var visibility: Int?               = null,
    var wind: Wind? = Wind(),
    var clouds: Clouds? = Clouds(),
    var dt: Int?               = null,
    var sys: Sys? = Sys(),
    var timezone: Int?               = null,
    var id: Int?               = null,
    var name: String?            = null,
    var cod: Int?               = null

)
data class Coord (

  var lon : Double? = null,
  var lat : Double? = null

)
data class Weather (

    var id          : Int?    = null,
     var main        : String? = null,
     var description : String? = null,
    var icon        : String? = null

)

data class Main (

 var temp      : Double? = null,
   var feelsLike : Double? = null,
 var tempMin   : Double? = null,
 var tempMax   : Double? = null,
 var pressure  : Int?    = null,
 var humidity  : Int?    = null

)

data class Wind (

    var speed : Double? = null,
    var deg   : Int?    = null,
   var gust  : Double? = null

)


data class Clouds (

    var all : Int? = null

)

data class Sys (

  var type    : Int?    = null,
 var id      : Int?    = null,
     var country : String? = null,
     var sunrise : Int?    = null,
 var sunset  : Int?    = null

)*/