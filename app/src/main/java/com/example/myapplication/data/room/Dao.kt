package com.example.myapplication.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.models.roomEntity.ModelCurrentEntity
import com.example.myapplication.data.models.roomEntity.ModelEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM currentweather")
    fun currentweatherGet(): ModelCurrentEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun currentweatherInsert(users: ModelCurrentEntity)

    @Query("SELECT * FROM listforecastweather")
    fun forecastGet(): List<ModelEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun forecastInsert(users: List<ModelEntity>)

    @Query("DELETE  FROM listforecastweather")
    fun forecastDel()
}

