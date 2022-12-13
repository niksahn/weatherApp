package com.example.myapplication.ui.fragments.recycle

import com.example.myapplication.data.models.Model
import com.example.myapplication.data.models.placeholder.PlaceholderContent
import com.example.myapplication.data.models.placeholder.PlaceholderItemContent
import java.lang.Double
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.round

fun putingInPlaceHolder(it: List<Model>){
    PlaceholderContent.clear()
    var begin=1
    val begindate=it[0].list[0]?.dtTxt
    for( i in 1 until it[0].list.size-1)
    {

        if(it[0].list[i]?.dtTxt?.substring(0,10)!=begindate?.substring(0,10))
        {
            break
        }
        else{
            begin+=1}



    }
    for( i in begin until (it[0].list.size-1) step 8){

        var minTemp=10000.0
        var maxTemp=0.0
        var avHumidity=0
        var icon=it[0].list[i]?.weather?.get(0)?.icon!!
        for (ii in i until Double.min(i + 7.0, (it[0].list.size - 1).toDouble()).toInt())
        {
            minTemp= Double.min(minTemp, it[0].list[ii].main?.temp!!)
            maxTemp= Double.max(maxTemp, it[0].list[ii].main?.temp!!)

            if(it[0].list[i]?.weather?.get(0)?.icon!!.substring(0,1)>icon.substring(0,1)){
                icon=it[0].list[ii]?.weather?.get(0)?.icon!!.substring(0,1)+"d"
            }
            avHumidity+=it[0].list[ii]?.main?.humidity?:0



        }

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:s")
        val date = LocalDate.parse(it[0].list[i]?.dtTxt, formatter).dayOfWeek

        PlaceholderContent.addItem(i, PlaceholderItemContent(minTemp.minus(273.15) ?.let { round(it) }?.toInt(),maxTemp.minus(273.15) ?.let { round(it) }?.toInt(),icon,(avHumidity/8),date.toString()))

    }
}