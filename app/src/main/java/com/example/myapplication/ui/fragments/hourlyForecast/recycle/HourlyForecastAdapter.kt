package com.example.myapplication.ui.fragments.hourlyForecast.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentHourlyForecast2Binding
import com.example.myapplication.ui.fragments.hourlyForecast.recycle.PlaceholderHourlyForecastContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class HourlyForecastAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<HourlyForecastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentHourlyForecast2Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.time.text = item.content.dtTxt
        holder.temp.text = item.content.temp.toString() + "\u00B0"
        Glide
            .with(holder.iconc)
            .load("http://openweathermap.org/img/w/${item.content.icon}.png")
            .into(holder.iconc)
        holder.humidity.text = item.content.humidity.toString() + "%"

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentHourlyForecast2Binding) :
        RecyclerView.ViewHolder(binding.root) {
        val temp: TextView = binding.temp
        val humidity: TextView = binding.hummidity
        val time: TextView = binding.time
        val iconc: ImageView = binding.icon


    }

}