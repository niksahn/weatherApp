package com.example.myapplication.ui.fragments.hourlyForecast.placeholder

import com.example.myapplication.Constants.formatter
import com.example.myapplication.data.models.Model

import java.lang.Double
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.round

fun putingInPlaceHolder2(it: List<Model>,numder:Int){
    println(it)
    //val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:s")
    PlaceholderContent2.clear()
    var minTemp=10000.0
    var maxTemp=0.0
    var begin=1
    val begindate=it[0].dtTxt
    var num=0
if (numder!=0){num=numder-1}
    for( i in 1 until it.size-1)
    {

        if(it[i]?.dtTxt?.substring(0,10)!=begindate?.substring(0,10))
        {
            break
        }
        else{
            begin+=1}
    }
    Daycard.icon=it[0]?.icon!!.substring(0,2)+"d"
    for( i in begin+num*8 until Double.min( begin+num*8+ 8.0, (it.size - 1).toDouble()).toInt()){

        val date = it[i]?.dtTxt?.substring(11,16)
        minTemp= Double.min(minTemp, it[i]?.temp!!)
        maxTemp= Double.max(maxTemp, it[i]?.temp!!)

        if(it[i]?.icon!!.substring(0,2)> Daycard.icon!!){
            Daycard.icon=it[i]?.icon!!.substring(0,2)+"d"

        }
        PlaceholderContent2.addItem(i, PlaceholderItemContent2(it[i].temp?.minus(273.15) ?.let { round(it) }?.toInt(),it[i].humidity,it[i].icon,date))
        Daycard.date = LocalDate.parse(it[i-1]?.dtTxt, formatter).dayOfWeek.toString().toLowerCase()+" "+ LocalDate.parse(it[i-1]?.dtTxt, formatter).dayOfMonth.toString().toLowerCase()+" "+ LocalDate.parse(it[i-1]?.dtTxt, formatter).month.toString().toLowerCase()
    }
    Daycard.temp=minTemp.minus(273.15) ?.let { round(it) }?.toInt().toString()+"\u00B0"

    println(PlaceholderContent2.ITEMS)
}
object Daycard {
    var icon: String? = null
    var temp: String? = null
    var city: String? = null
    var date:String? = null
}