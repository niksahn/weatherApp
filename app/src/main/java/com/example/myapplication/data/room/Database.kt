package com.example.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.models.roomEntity.ModelCurrentEntity
import com.example.myapplication.data.models.roomEntity.ModelEntity

@Database(
    entities = [ModelCurrentEntity::class, ModelEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val weatherDao: WeatherDao
}