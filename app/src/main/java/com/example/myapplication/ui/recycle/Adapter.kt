package com.example.myapplication.ui.recycle



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R


/*
class CustomRecyclerAdapter :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {
    var string: String = ""
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    /*var name: List<ListItemData> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }*/
   // var click: ((i: Int, holder: MyViewHolder, name: List<ListItemData>) -> Unit?)? = null

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val cont = item.context


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        //val item = LayoutInflater.from(parent.context).inflate(R.layout.WeatherFr, parent, false)
        return MyViewHolder(item)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       /* holder.title.text = name[position].name

        holder.subtitle.text =
            "type: " + name[position].species + ", status:" + name[position].status
        holder.itemView.setOnClickListener { click?.let { it1 -> it1(position, holder, name) } }
        Glide
            .with(holder.imageView1)
            .load(name[position].image)
            .into(holder.imageView1)
        if (name[position].name?.contains(string) == false) {
            holder.itemView.layoutParams.height = 0
            holder.itemView.visibility = View.GONE

        } else {
            holder.itemView.layoutParams.height = 750
            holder.itemView.visibility = View.VISIBLE
        }*/
    }

    override fun getItemCount() = 1
}
*/
