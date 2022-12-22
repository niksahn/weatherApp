package com.example.myapplication.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.*
import com.example.myapplication.R
import com.example.myapplication.dimodule.askForLocationPermissions
import com.example.myapplication.domain.repository.CityRepository
import com.example.myapplication.ui.fragments.currentWeather.WeatherFr
import com.example.myapplication.ui.fragments.forecast.recycle.ItemFragment
import com.example.myapplication.ui.fragments.hourlyForecast.HourlyForecastFr
import com.example.myapplication.ui.fragments.hourlyForecast.placeholder.Daycard
import org.koin.androidx.viewmodel.ext.android.viewModel
var permission=0
class MainActivity : AppCompatActivity() {
     var icon:String?=null
    var curWetherframe:Fragment=WeatherFr()
    var forecastWeath:Fragment=ItemFragment()
    var hourlyForecastWeath:Fragment=HourlyForecastFr()
    var ft =  getSupportFragmentManager()
    private val viewModel: ViewModel by viewModel()
    private lateinit var toolbar:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar=findViewById(R.id.toolbar)
        checkPermission()
        ft.beginTransaction().replace(R.id.fragmentContainerView,curWetherframe)
            .replace(R.id.fragmentContainerView2,forecastWeath)
            .commit()
        if (permission==1){
        viewModel.weather.observe(this) {
            icon = it.icon
            println(icon)
            icon = icon?.substring(0, 2)
            println(icon)
            //icon="13"
            var draw = this.resources.getIdentifier("d$icon", "drawable", this.packageName)
            toolbar.setBackgroundResource(draw)
        }
        }


        viewModel.fragment.observe(this){
            println("ELEM "+it)
            if (it==0){ ft.beginTransaction().replace(R.id.fragmentContainerView,curWetherframe)
                .commit()}
            else{
                ft.beginTransaction().replace(R.id.fragmentContainerView,hourlyForecastWeath).commit()
            }

        }


    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@MainActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION) ===
                                PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                        permission=1
                        viewModel.weather.observe(this) {
                            icon = it.icon
                            println(icon)
                            icon = icon?.substring(0, 2)
                            println(icon)
                            //icon="13"
                            var draw = this.resources.getIdentifier("d$icon", "drawable", this.packageName)
                            toolbar.setBackgroundResource(draw)


                        }

                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                    permission=0
                    finish()
                }
                return
            }
        }
    }
    fun checkPermission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            &&
            ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            askForLocationPermissions(this@MainActivity)
        }else{ permission=1}
    }

}