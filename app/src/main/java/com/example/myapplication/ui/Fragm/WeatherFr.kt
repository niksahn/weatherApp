package com.example.myapplication.Fragm

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.models.Model
import com.example.myapplication.data.models.ModelApiCurrent
import com.example.myapplication.ui.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.round

class WeatherFr : Fragment() {

    companion object {
        var  weatherfr: ModelApiCurrent?=ModelApiCurrent();
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
        val wind: TextView =   view.findViewById(R.id.windval)
        val pressure: TextView =   view.findViewById(R.id.pressureval)
        val humidity: TextView =   view.findViewById(R.id.humidityval)
        val image:ImageView=view.findViewById(R.id.imageView2)
        println(weatherfr)
        println("YEEEEEEEEEEEEEEE")
        viewModel.weather.observe(viewLifecycleOwner) {
            weatherfr=it;
            val k= weatherfr?.main?.temp?.minus(273.15)?.let { it1 -> round(it1).toInt() };
            temp.text=(k).toString()+"\u00B0"
            date.text= weatherfr?.weather?.get(0)?.description
            city.text=weatherfr?.name
            pressure.text= (weatherfr?.main?.pressure?.times(0.750063755419211)?.let { it1 -> round(it1).toInt() }).toString()+" мм р.ст."
            humidity.text=weatherfr?.main?.humidity.toString()+ "%"
            wind.text=weatherfr?.wind?.speed.toString()+" м/с"
            Glide
                .with(image)
                .load("http://openweathermap.org/img/w/${weatherfr?.weather?.get(0)?.icon}.png")
                .into(image)
        }
        //

        }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //viewModel = ViewModelProvider(this).get(BlankViewModel::class.java)
        // TODO: Use the ViewModel
    }

}


