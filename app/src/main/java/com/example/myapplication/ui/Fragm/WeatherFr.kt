package com.example.myapplication.Fragm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.myapplication.R
import com.example.myapplication.data.models.Model
import com.example.myapplication.ui.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherFr : Fragment() {

    companion object {
        fun newInstance() = WeatherFr()
    }

    //private lateinit var viewModel: BlankViewModel
    private val viewModel: ViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       var  weatherfr: Model=Model();
        viewModel.weather.observe(viewLifecycleOwner) {
            weatherfr=it;
        }
        val city: TextView = container!!.findViewById(R.id.textView)
        val date: TextView =   container!!.findViewById(R.id.textView2)
        city.text=weatherfr.name;
        date.text=weatherfr.weather[0].description
        return inflater.inflate(R.layout.main_weather_now, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(BlankViewModel::class.java)
        // TODO: Use the ViewModel
    }

}