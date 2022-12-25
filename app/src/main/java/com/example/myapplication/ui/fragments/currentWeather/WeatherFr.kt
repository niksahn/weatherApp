package com.example.myapplication.ui.fragments.currentWeather


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.Constants
import com.example.myapplication.R
import com.example.myapplication.data.models.modelForviewModel.ModelCurrent
import com.example.myapplication.ui.ViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.round

class WeatherFr : Fragment() {

    companion object {
        var weatherfr: ModelCurrent? = ModelCurrent()
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
        val temp: TextView = view.findViewById(R.id.textView)
        val date: TextView = view.findViewById(R.id.textView2)
        val city: TextView = view.findViewById(R.id.textView3)
        val wind: TextView = view.findViewById(R.id.windval)
        val pressure: TextView = view.findViewById(R.id.pressureval)
        val humidity: TextView = view.findViewById(R.id.humidityval)
        val image: ImageView = view.findViewById(R.id.imageView2)

        viewModel.weather.observe(viewLifecycleOwner) {
            weatherfr = it

            val k = weatherfr?.temp?.minus(273.15)?.let { it1 -> round(it1).toInt() }
            temp.text = (k).toString() + "\u00B0"
            date.text = weatherfr?.description
            city.text = weatherfr?.name
            pressure.text = (weatherfr?.pressure?.times(0.750063755419211)
                ?.let { it1 -> round(it1).toInt() }).toString() + Constants.units[1]
            humidity.text = weatherfr?.humidity.toString() + "%"
            wind.text = weatherfr?.speed.toString() + Constants.units[0]
            Glide
                .with(image)
                .load("http://openweathermap.org/img/w/${weatherfr?.icon}.png")
                .into(image)


        }

    }
}


