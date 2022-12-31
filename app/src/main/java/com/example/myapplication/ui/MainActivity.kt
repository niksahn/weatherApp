package com.example.myapplication.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.myapplication.Constants.language
import com.example.myapplication.R
import com.example.myapplication.dimodule.askForLocationPermissions
import com.example.myapplication.ui.fragments.currentWeather.WeatherFr
import com.example.myapplication.ui.fragments.forecast.recycle.ItemFragment
import com.example.myapplication.ui.fragments.hourlyForecast.HourlyForecastFr
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

var permission = 0

class MainActivity : AppCompatActivity() {
    var icon: String? = null
    lateinit var updatedate:TextView
    var curWetherframe: Fragment = WeatherFr()
    var forecastWeath: Fragment = ItemFragment()
    var hourlyForecastWeath: Fragment = HourlyForecastFr()
    var ft = supportFragmentManager
    private val viewModel: ViewModel by viewModel()
    private lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updatedate=findViewById(R.id.date)
        toolbar = findViewById(R.id.toolbar)
        checkPermission()
        curweather()
        viewModel.fragment.observe(this) {
            println("ELEM " + it)
            if (it == 0) {
                ft.beginTransaction().replace(R.id.fragmentContainerView, curWetherframe)
                    .commit()
            } else {
                ft.beginTransaction().replace(R.id.fragmentContainerView, hourlyForecastWeath)
                    .commit()
            }
        }
        viewModel.time.observe(this){
            var now= it?.let { it1 -> Date(it1) }
            val locale = Locale(language)
            Locale.setDefault(locale)
            updatedate.text=  // длинная строка
                    String.format(locale, " %tD ", now) + //(MM/DD/YY)

                    String.format(locale, "%tr\n", now)  //Full 12-hour time
                      //Localized time zone abbreviation
        }

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    if ((ContextCompat.checkSelfPermission(
                            this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) ===
                                PackageManager.PERMISSION_GRANTED)
                    ) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                        permission = 1
                        curweather()

                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                    permission = 0
                    finish()
                }
                return
            }
        }
    }

    fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
            &&
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            askForLocationPermissions(this@MainActivity)
        } else {
            permission = 1
        }
    }
    fun curweather(){ viewModel.weather.observe(this) {
        if (it != null) {
            ft.beginTransaction().replace(R.id.fragmentContainerView, curWetherframe)
                .replace(R.id.fragmentContainerView2, forecastWeath)
                .commit()
        }

        icon = it.icon
        icon = icon?.substring(0, 2)
        var draw = this.resources.getIdentifier("d$icon", "drawable", this.packageName)
        toolbar.setBackgroundResource(draw)
    }}

}