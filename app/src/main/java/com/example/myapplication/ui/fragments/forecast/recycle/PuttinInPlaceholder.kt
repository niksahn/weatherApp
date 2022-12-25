package com.example.myapplication.ui.fragments.forecast.recycle

import com.example.myapplication.Constants.formatter
import com.example.myapplication.data.models.Model
import com.example.myapplication.data.models.placeholder.PlaceholderContent
import com.example.myapplication.data.models.placeholder.PlaceholderItemContent
import java.lang.Double
import java.time.LocalDate
import kotlin.let
import kotlin.math.round

fun putingInForecastPlaceHolder(it: List<Model>) {
    println(it)
    PlaceholderContent.clear()
    val begindate1 = it[0].dtTxt
    PlaceholderContent.addItem(
        0,
        PlaceholderItemContent(
            it[0].temp?.minus(273.15)?.let { round(it) }?.toInt(),
            it[0].temp?.minus(273.15)?.let { round(it) }?.toInt(),
            it[0].icon,
            (it[0].humidity),
            "CURRENT"
        )
    )
    var i = 0
    while (i < (it.size - 1)) {
        var minTemp = 10000.0
        var maxTemp = 0.0
        var avHumidity = 0
        val begindate = it[i].dtTxt
        var icon = it[i].icon!!
        var countHours = 1

        while (i < (it.size - 1)) {
            minTemp = Double.min(minTemp, it[i].temp!!)
            maxTemp = Double.max(maxTemp, it[i].temp!!)
            if (it[i].icon!!.substring(0, 2) > icon.substring(0, 2)) {
                icon = it[i].icon!!.substring(0, 2) + "d"
            }
            countHours += 1
            i += 1
            avHumidity += it[i].humidity ?: 0
            if (it[i].dtTxt?.substring(0, 11) != begindate?.substring(0, 11)) {
                break
            }
        }

        println(PlaceholderContent.ITEMS)
        var date = LocalDate.parse(it[i - 1].dtTxt, formatter).dayOfWeek.toString().substring(0, 3)
        if (begindate1?.substring(0, 11) == begindate?.substring(0, 11)) date = "TODAY"
        PlaceholderContent.addItem(
            i,
            PlaceholderItemContent(
                minTemp.minus(273.15).let { round(it) }?.toInt(),
                maxTemp.minus(273.15).let { round(it) }?.toInt(),
                icon,
                (avHumidity / (countHours - 1)),
                date
            )
        )

    }
}