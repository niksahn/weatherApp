package com.example.myapplication.ui.Fragm

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.models.Model
import com.example.myapplication.data.models.placeholder.PlaceholderContent
import com.example.myapplication.data.models.placeholder.PlaceholderItemContent
import com.example.myapplication.ui.ViewModel
import com.example.myapplication.ui.recycle.MyItemRecyclerViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Double.max
import java.lang.Double.min
import java.util.*

/**
 * A fragment representing a list of Items.
 */
class ItemFragment : Fragment() {

    private var columnCount = 1
    private val viewModel: ViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.elem_weather_forecast, container, false)


        viewModel.forecast.observe(viewLifecycleOwner) {
            /*val calendar: Calendar = Calendar.getInstance()
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val formattedDate: String = formatter.format(calendar.getTime())*/
            putingInPlaceHolder(it)
            if (view is RecyclerView) {
                with(view) {
                    layoutManager = when {
                        columnCount <= 1 -> LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
                        else -> GridLayoutManager(context, columnCount)
                    }
                    adapter = MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS)
                }
            }

        }

        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}




fun putingInPlaceHolder(it: List<Model>){
    var begin=1
    val begindate=it[0].list[0]?.dtTxt
    for( i in 1 until it[0].list.size)
    {

        if(it[0].list[i]?.dtTxt?.substring(0,10)!=begindate?.substring(0,10))
        {
            break
        }
        else{
            begin+=1}



    }
    for( i in begin until (it[0].list.size) step 8){
        var minTemp=10000.0
        var maxTemp=0.0
        var avHumidity=0
        var icon=""
        for (ii in i until i+8 step 8)
        {
            minTemp=min(minTemp,it[0].list[i].main?.temp?:10000.0)
            maxTemp=max(maxTemp,it[0].list[i].main?.temp?:0.0)
            if(it[0].list[i]?.weather?.get(0)?.icon!! >icon){
                icon=it[0].list[i]?.weather?.get(0)?.icon!!
            }
            avHumidity+=it[0].list[i]?.main?.humidity?:0
        }
        PlaceholderContent.addItem(i, PlaceholderItemContent(minTemp,maxTemp,icon,(avHumidity/8),it[0].list[i]?.dtTxt))

    }
}