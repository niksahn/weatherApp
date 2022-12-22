package com.example.myapplication.ui.fragments.forecast.recycle

import com.example.myapplication.Constants.formatter
import com.example.myapplication.data.models.Model
import com.example.myapplication.data.models.placeholder.PlaceholderContent
import com.example.myapplication.data.models.placeholder.PlaceholderItemContent
import java.lang.Double
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.round

fun putingInPlaceHolder(it: List<Model>){
    println(it)

    PlaceholderContent.clear()
    var begin=0

    val begindate=it[0].dtTxt
    PlaceholderContent.addItem(0, PlaceholderItemContent(it[0].temp?.minus(273.15) ?.let { round(it) }?.toInt(),it[0].temp?.minus(273.15) ?.let { round(it) }?.toInt(),it[0].icon,(it[0].humidity), LocalDate.parse(it[0]?.dtTxt, formatter).dayOfWeek.toString().substring(0,3)))
    for( i in 1 until it.size-1)
    {

        if(it[i]?.dtTxt?.substring(0,10)!=begindate?.substring(0,10))
        {
            break
        }
        else{
            begin+=1}



    }

    for( i in begin until (it.size-1) step 8){

        var minTemp=10000.0
        var maxTemp=0.0
        var avHumidity=0
        var icon=it[i]?.icon!!
        for (ii in i until Double.min(i + 7.0, (it.size - 1).toDouble()).toInt())
        {
            minTemp= Double.min(minTemp, it[ii]?.temp!!)
            maxTemp= Double.max(maxTemp, it[ii]?.temp!!)

            if(it[i]?.icon!!.substring(0,1)>icon.substring(0,1)){
                icon=it[ii]?.icon!!.substring(0,1)+"d"
            }
            avHumidity+=it[ii]?.humidity?:0




        }


        val date = LocalDate.parse(it[i+1]?.dtTxt, formatter).dayOfWeek

        PlaceholderContent.addItem(i, PlaceholderItemContent(minTemp.minus(273.15) ?.let { round(it) }?.toInt(),maxTemp.minus(273.15) ?.let { round(it) }?.toInt(),icon,(avHumidity/8),date.toString().substring(0,3) ))

    }
}