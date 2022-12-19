package com.example.myapplication.domain.repository

import android.location.Location

interface CityRepository {
    fun getCity(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray): Location?
}