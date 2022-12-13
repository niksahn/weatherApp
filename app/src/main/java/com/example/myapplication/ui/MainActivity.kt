package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import com.example.myapplication.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
     var icon:String?=null
    private val viewModel: ViewModel by viewModel()
    private lateinit var toolbar:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar=findViewById(R.id.toolbar)
        viewModel.weather.observe(this) {
            icon = it.icon
            println(icon)
            icon = icon?.substring(0, 2)
            println(icon)
            //icon="50"
            var draw = this.resources.getIdentifier("d$icon", "drawable", this.packageName)
            toolbar.setBackgroundResource(draw)
        }
         // mainFrame=findViewById(R.id.mainframe)
        //layoutInflater.inflate(R.layout.main_weather_now,mainFrame , false)


    }
}