package com.example.myapplication.ui.fragments.hourlyForecast.recycle

import com.example.myapplication.Constants.formatter
import com.example.myapplication.data.models.Model
import java.lang.Double
import java.time.LocalDate
import java.util.*
import kotlin.Int
import kotlin.String
import kotlin.let
import kotlin.math.round
import kotlin.toString

fun putingInHourlyForecastPlaceHolder(it: List<Model>, numder: Int) {
    println(it)
    PlaceholderHourlyForecastContent.clear()
    var minTemp = 10000.0
    var maxTemp = 0.0
    var begin = 1
    val begindate1 = it[0].dtTxt
    var num = 0
    if (numder != 0) {
        num = numder - 2
    }

    for (i in 1 until it.size - 1) {
        if (it[i].dtTxt?.substring(0, 10) != begindate1?.substring(0, 10)) {
            break
        } else {
            begin += 1

        }
    }
    var i = 0
    var begindate = begindate1
    Daycard.icon = it[0].icon!!.substring(0, 2) + "d"
    if (num != -1) {
        begindate = it[num * 8 + begin].dtTxt
        i = num * 8 + begin
    }

    while (i < it.size - 1) {

        if (it[i].dtTxt?.substring(0, 10) != begindate?.substring(0, 10)) {
            break
        }

        val date = it[i].dtTxt?.substring(11, 16)
        minTemp = Double.min(minTemp, it[i].temp!!)
        maxTemp = Double.max(maxTemp, it[i].temp!!)

        if (it[i].icon!!.substring(0, 2) > Daycard.icon!!) {
            Daycard.icon = it[i].icon!!.substring(0, 2) + "d"

        }
        PlaceholderHourlyForecastContent.addItem(
            i,
            PlaceholderHourlyForecastItemContent(
                it[i].temp?.minus(273.15)?.let { round(it) }?.toInt(),
                it[i].humidity,
                it[i].icon,
                date
            )
        )
        Daycard.date = LocalDate.parse(it[i].dtTxt, formatter).dayOfWeek.toString()
            .lowercase(Locale.getDefault()) + " " + LocalDate.parse(
            it[i].dtTxt,
            formatter
        ).dayOfMonth.toString().lowercase(Locale.getDefault()) + " " + LocalDate.parse(
            it[i].dtTxt,
            formatter
        ).month.toString().lowercase(Locale.getDefault())
        i += 1
    }
    Daycard.temp = minTemp.minus(273.15).let { round(it) }?.toInt().toString() + "\u00B0"


}

object Daycard {
    var icon: String? = null
    var temp: String? = null
    var city: String? = null
    var date: String? = null
}