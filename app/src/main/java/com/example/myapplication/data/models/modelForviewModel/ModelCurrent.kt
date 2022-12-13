package com.example.myapplication.data.models.modelForviewModel

/*data class ModelCurrent (

    var coord      : CoordCurrent?             = CoordCurrent(),
    var weather    : ArrayList<WeatherCurrent> = arrayListOf(),
    var base       : String?            = null,
    var main       : MainCurrent?              = MainCurrent(),
    var visibility : Int?               = null,
    var wind       : WindCurrent?              = WindCurrent(),
    var clouds     : CloudsCurrent?            = CloudsCurrent(),
    var dt         : Int?               = null,
    var sys        : SysCurrent?               = SysCurrent(),
    var timezone   : Int?               = null,
    var id         : Int?               = null,
    var name       : String?            = null,
    var cod        : Int?               = null

)*/
data class ModelCurrent(
    var id: Int?=0,

    var base       : String?            = null,

    var visibility : Int?               = null,

    var dt         : Int?               = null,
    var timezone   : Int?               = null,
    var name       : String?            = null,
    var cod        : Int?               = null,


    var icon        : String? = null,
    var description : String? = null,

    var temp      : Double? = null,

    var pressure  : Int?    = null,
    var humidity  : Int?    = null,

    var speed : Double? = null,

    )

/* data class CoordCurrent (

    var lon : Double? = null,
    var lat : Double? = null

)
data class WeatherCurrent (

    var id          : Int?    = null,
    var main        : String? = null,
    var description : String? = null,
    var icon        : String? = null

)
data class MainCurrent (

    var temp      : Double? = null,
    var feelsLike : Double? = null,
    var tempMin   : Double? = null,
    var tempMax   : Double? = null,
    var pressure  : Int?    = null,
    var humidity  : Int?    = null

)

data class WindCurrent (

    var speed : Double? = null,
    var deg   : Int?    = null

)
data class CloudsCurrent (

    var all : Int? = null

)
data class SysCurrent (

    var type    : Int?    = null,
    var id      : Int?    = null,
    var country : String? = null,
    var sunrise : Int?    = null,
    var sunset  : Int?    = null

) */