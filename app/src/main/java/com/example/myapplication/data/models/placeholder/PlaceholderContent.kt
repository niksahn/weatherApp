package com.example.myapplication.data.models.placeholder

object PlaceholderContent {


    val ITEMS: MutableList<PlaceholderItem> = ArrayList()


    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()




    fun addItem(position: Int, vals: PlaceholderItemContent) {
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



    data class PlaceholderItem(val id: String, val content: PlaceholderItemContent)

}

data class PlaceholderItemContent(
    val minTemp: Int? = null,
    val maxTemp: Int? = null,
    var icon: String? = null,
    var humidity: Int? = null,
    var date: String? = null,


    )
