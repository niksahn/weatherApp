package com.example.myapplication.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.data.repository.CityRepositoryImpl
import com.example.myapplication.dimodule.askForLocationPermissions
import com.example.myapplication.domain.repository.CityRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
     var icon:String?=null
    private val viewModel: ViewModel by viewModel()
    private lateinit var toolbar:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar=findViewById(R.id.toolbar)

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
            &&
            ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            askForLocationPermissions(this@MainActivity)
        }
        viewModel.weather.observe(this) {
            icon = it.icon
            println(icon)
            icon = icon?.substring(0, 2)
            println(icon)
            //icon="13"
            var draw = this.resources.getIdentifier("d$icon", "drawable", this.packageName)
            toolbar.setBackgroundResource(draw)
        }
         // mainFrame=findViewById(R.id.mainframe)
        //layoutInflater.inflate(R.layout.main_weather_now,mainFrame , false)


    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
       viewModel.permissionReqCode.postValue(requestCode)
        viewModel.permissionReqGranted.postValue(grantResults)
    }

}