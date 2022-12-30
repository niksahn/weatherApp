package com.example.myapplication

import java.time.format.DateTimeFormatter

object Constants {
    val APP_PREFERENCES = "timeUpdate"
    val APP_PREFERENCES_TIME = "time_currentWeather_update"
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:s")
    val baseurl = "https://api.openweathermap.org"
    val language = "en"
    val units: List<String> = listOf(" m/s", " mm Hg")
}