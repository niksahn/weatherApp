package com.example.myapplication.ui.fragments.forecast.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.models.placeholder.PlaceholderContent.PlaceholderItem
import com.example.myapplication.databinding.FragmentItemBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyItemRecyclerViewAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {
    var click: ((i: Int, holder: ViewHolder) -> Unit?)? = null
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
        holder.tempView.text =
            item.content.maxTemp.toString() + "\u00B0" + "/" + (item.content.minTemp).toString() + "\u00B0"
        Glide
            .with(holder.iconc)
            .load("http://openweathermap.org/img/w/${item.content.icon}.png")
            .into(holder.iconc)
        holder.humidityView.text = item.content.humidity.toString() + "%"
        holder.itemView.setOnClickListener { click?.let { it1 -> it1(position, holder) } }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tempView: TextView = binding.tempMin
        val dateView: TextView = binding.date
        val humidityView: TextView = binding.humidity
        val iconc: ImageView = binding.imageView


    }

}