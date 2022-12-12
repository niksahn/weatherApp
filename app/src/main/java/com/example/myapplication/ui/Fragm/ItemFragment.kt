package com.example.myapplication.ui.Fragm

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
import com.example.myapplication.ui.Fragm.recycle.MyItemRecyclerViewAdapter
import com.example.myapplication.ui.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Double.max
import java.lang.Double.min
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.math.round

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




        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val  adapterr = MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS)
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter =adapterr
            }
        }
        viewModel.forecast.observe(viewLifecycleOwner) {
            putingInPlaceHolder(it)
            adapterr.notifyDataSetChanged()}


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
        for (ii in i until min(i+7.0, (it[0].list.size-1).toDouble()).toInt())
        {
            minTemp=min(minTemp,it[0].list[ii].main?.temp!!)
            maxTemp=max(maxTemp,it[0].list[ii].main?.temp!!)

            if(it[0].list[i]?.weather?.get(0)?.icon!!.substring(0,1)>icon.substring(0,1)){
                icon=it[0].list[ii]?.weather?.get(0)?.icon!!.substring(0,1)+"d"
            }
            avHumidity+=it[0].list[ii]?.main?.humidity?:0



        }
        val c = Calendar.getInstance()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:s")
        val date = LocalDate.parse(it[0].list[i]?.dtTxt, formatter).dayOfWeek

        PlaceholderContent.addItem(i, PlaceholderItemContent(minTemp.minus(273.15) ?.let { round(it) }?.toInt(),maxTemp.minus(273.15) ?.let { round(it) }?.toInt(),icon,(avHumidity/8),date.toString()))

    }
}