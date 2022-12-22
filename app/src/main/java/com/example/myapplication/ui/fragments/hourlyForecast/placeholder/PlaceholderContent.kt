package com.example.myapplication.ui.fragments.hourlyForecast.placeholder

import java.util.ArrayList
import java.util.HashMap


object PlaceholderContent2 {

    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

    fun addItem(position: Int,vals: PlaceholderItemContent2)  {
        ITEMS.add(PlaceholderItem(position.toString(),vals  ))
        ITEM_MAP.put(PlaceholderItem(position.toString(),vals  ).id, PlaceholderItem(position.toString(),vals  ))

    }
    fun clear()  {
        ITEMS.clear()
        ITEM_MAP.clear()
    }


    data class PlaceholderItem(val id: String, val content: PlaceholderItemContent2)

}
data class PlaceholderItemContent2(
    var temp: Int? = null,
    var humidity: Int? = null,
    var icon: String? = null,
    var dtTxt: String? = null


    )