package com.example.myapplication.ui.fragments.hourlyForecast.recycle


object PlaceholderHourlyForecastContent {

    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()

    fun addItem(position: Int, vals: PlaceholderHourlyForecastItemContent) {
        ITEMS.add(PlaceholderItem(position.toString(), vals))
        ITEM_MAP.put(
            PlaceholderItem(position.toString(), vals).id,
            PlaceholderItem(position.toString(), vals)
        )

    }

    fun clear() {
        ITEMS.clear()
        ITEM_MAP.clear()
    }


    data class PlaceholderItem(val id: String, val content: PlaceholderHourlyForecastItemContent)

}

data class PlaceholderHourlyForecastItemContent(
    var temp: Int? = null,
    var humidity: Int? = null,
    var icon: String? = null,
    var dtTxt: String? = null


)