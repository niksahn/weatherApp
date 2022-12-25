package com.example.myapplication.ui.fragments.hourlyForecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.ui.ViewModel
import com.example.myapplication.ui.fragments.hourlyForecast.recycle.Daycard
import com.example.myapplication.ui.fragments.hourlyForecast.recycle.HourlyForecastAdapter
import com.example.myapplication.ui.fragments.hourlyForecast.recycle.PlaceholderHourlyForecastContent
import com.example.myapplication.ui.fragments.hourlyForecast.recycle.putingInHourlyForecastPlaceHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A fragment representing a list of Items.
 */
class HourlyForecastFr : Fragment() {
    private val viewModel: ViewModel by viewModel()
    private var columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_hourly_forecast_list, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val frame: FrameLayout = view.findViewById(R.id.frameLayout)
        val temp: TextView = frame.findViewById(R.id.textView)
        val image: ImageView = frame.findViewById(R.id.imageView2)
        val date: TextView = frame.findViewById(R.id.textView2)
        val city: TextView = frame.findViewById(R.id.textView3)
        val adapterr = HourlyForecastAdapter(PlaceholderHourlyForecastContent.ITEMS)
        val recyclerView: RecyclerView = view.findViewById(R.id.list)
        if (recyclerView is RecyclerView) {
            with(recyclerView) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = adapterr

            }
        }

        viewModel.forecast.observe(viewLifecycleOwner) {
            var list = it
            viewModel.fragment.observe(viewLifecycleOwner) {
                viewModel.weather.observe(viewLifecycleOwner) { city.text = it.name }
                putingInHourlyForecastPlaceHolder(list, it)
                date.text = Daycard.date
                temp.text = Daycard.temp
                Glide
                    .with(image)
                    .load("http://openweathermap.org/img/w/${Daycard.icon}.png")
                    .into(image)
                adapterr.notifyDataSetChanged()
            }
            adapterr.notifyDataSetChanged()
        }

    }


}