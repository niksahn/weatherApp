package com.example.myapplication.data.models.placeholder

import com.example.myapplication.data.models.Model
import java.util.ArrayList
import java.util.HashMap

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object PlaceholderContent {

    /**
     * An array of sample (placeholder) items.
     */
    val ITEMS: MutableList<PlaceholderItem> = ArrayList()

    /**
     * A map of sample (placeholder) items, by ID.
     */
    val ITEM_MAP: MutableMap<String, PlaceholderItem> = HashMap()



    init {
        // Add some sample items.
       /* for (i in 1..COUNT) {
            addItem(createPlaceholderItem(i))
        }*/
    }



     fun addItem(position: Int,vals: PlaceholderItemContent)  {
         ITEMS.add(PlaceholderItem(position.toString(),vals  ))
         ITEM_MAP.put(PlaceholderItem(position.toString(),vals  ).id, PlaceholderItem(position.toString(),vals  ))

    }



    /**
     * A placeholder item representing a piece of content.
     */
    data class PlaceholderItem(val id: String, val content: PlaceholderItemContent)

}
data class PlaceholderItemContent(
    val minTemp:Int?=null,
    val maxTemp:Int?=null,
    var icon: String? = null,
    var humidity: Int? = null,
    var date: String? = null,


    )
