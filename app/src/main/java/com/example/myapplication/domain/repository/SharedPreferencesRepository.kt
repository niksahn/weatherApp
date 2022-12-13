package com.example.myapplication.domain.repository

import android.content.SharedPreferences
import com.example.myapplication.Constants

interface SharedPreferencesRepository {
     fun timeEditor(): SharedPreferences.Editor

     fun created(): Boolean
     fun timeSetting(): Long?
}