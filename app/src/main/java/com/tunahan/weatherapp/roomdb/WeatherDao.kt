package com.tunahan.weatherapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tunahan.weatherapp.model.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWeather(weather: Weather)

    @Delete
    suspend fun deleteWeather(weather: Weather)

    @Update
    suspend fun updateWeather(weather: Weather)

    @Query("SELECT * FROM weathers")
    suspend fun getAllWeather(): List<Weather>
}