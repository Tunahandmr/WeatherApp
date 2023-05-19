package com.tunahan.weatherapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tunahan.weatherapp.model.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: Weather)

    @Delete
    suspend fun deleteWeather(weather: Weather)

    @Update
    suspend fun updateWeather(weather: Weather)

    @Query("SELECT * FROM weathers")
    fun getAllWeather(): LiveData<List<Weather>>
}