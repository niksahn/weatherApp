package com.example.myapplication.ui.fragments.forecast.recycle

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.models.placeholder.PlaceholderContent.PlaceholderItem
import com.example.myapplication.databinding.FragmentItemBinding

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
        if  (item.content.maxTemp!=null){
            holder.dateView.text = item.content.date
            holder.tempView.text =
            item.content.maxTemp.toString() + "\u00B0" + "/" + (item.content.minTemp).toString() + "\u00B0"
            Glide
                .with(holder.iconc)
                .load("http://openweathermap.org/img/w/${item.content.icon}.png")
                .into(holder.iconc)
            holder.humidityView.text = item.content.humidity.toString() + "%"

        }
        else{
            holder.tempView.text="CURRENT"
            holder.waterdropimage.text=""
        }


        holder.itemView.setOnClickListener { click?.let { it1 -> it1(position, holder) } }

    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tempView: TextView = binding.tempMin
        val dateView: TextView = binding.date
        val humidityView: TextView = binding.humidity
        val iconc: ImageView = binding.imageView
        val waterdropimage: TextView = binding.textView5
    }

}