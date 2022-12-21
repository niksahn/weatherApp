package com.example.myapplication.ui

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.FragmentContainerView
import com.example.myapplication.R
import com.example.myapplication.dimodule.askForLocationPermissions
import com.example.myapplication.domain.repository.CityRepository
import org.koin.androidx.viewmodel.ext.android.viewModel
var permission=0
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
        }else{ permission=1}
        if (permission==1){
        viewModel.weather.observe(this) {
            icon = it.icon
            println(icon)
            icon = icon?.substring(0, 2)
            println(icon)
            //icon="13"
            var draw = this.resources.getIdentifier("d$icon", "drawable", this.packageName)
            toolbar.setBackgroundResource(draw)
        }}
        var mainFrame:FragmentContainerView=findViewById(R.id.fragmentContainerView)
        viewModel.fragment.observe(this){
            println("ELEM "+it)
        }

        //layoutInflater.inflate(R.layout.main_weather_now,mainFrame , false)


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

}