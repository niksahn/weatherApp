package com.example.myapplication.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.models.roomEntity.ModelCurrentEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM currentweather")
    fun currentweather(): ModelCurrentEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun currentweatherInsert(users: ModelCurrentEntity)
}

