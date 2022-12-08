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
import kotlin.math.round

class WeatherFr : Fragment() {

    companion object {
        var  weatherfr: Model=Model();
        fun newInstance() = WeatherFr()
    }

    //private lateinit var viewModel: BlankViewModel
    private val viewModel: ViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        return inflater.inflate(R.layout.main_weather_now, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        val temp: TextView =view.findViewById(R.id.textView)
        val date: TextView =   view.findViewById(R.id.textView2)
        val city: TextView =   view.findViewById(R.id.textView3)
        println(weatherfr)
        println("YEEEEEEEEEEEEEEE")
        viewModel.weather.observe(viewLifecycleOwner) {
            weatherfr=it;
            val k= round( weatherfr.main?.temp?.minus(273.15)!!);
            temp.text=(k).toString();
            date.text=weatherfr.weather[0].description
            city.text=weatherfr.name
        }
        //

        }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //viewModel = ViewModelProvider(this).get(BlankViewModel::class.java)
        // TODO: Use the ViewModel
    }

}


