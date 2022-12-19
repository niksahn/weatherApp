package com.example.myapplication.domain.repository

import android.location.Location

interface CityRepository {
    fun getCity(): Location?
    var permissionReqCode:Int

    var permissionReqGranted:IntArray?
}