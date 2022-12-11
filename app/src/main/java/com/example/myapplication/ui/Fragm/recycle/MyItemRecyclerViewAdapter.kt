package com.example.myapplication.ui.Fragm.recycle

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.myapplication.Fragm.WeatherFr

import com.example.myapplication.data.models.placeholder.PlaceholderContent.PlaceholderItem
import com.example.myapplication.databinding.FragmentItemBinding
import kotlin.math.round

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.dateView.text = item.content.date
        holder.tempView.text = item.content.maxTemp.toString()+"\u00B0" +"/"+(item.content.minTemp).toString()+"\u00B0"
        Glide
            .with(holder.iconc)
            .load("http://openweathermap.org/img/w/${item.content.icon}.png")
            .into(holder.iconc)
        holder.humidityView.text = item.content.humidity.toString()+"%"


    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tempView: TextView = binding.tempMin
        val dateView: TextView = binding.date
        val humidityView: TextView = binding.humidity
        val iconc: ImageView = binding.imageView



    }

}