package com.tunahan.weatherapp.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tunahan.weatherapp.model.Weather

@Database(entities = [Weather::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        val DATABASE_NAME = "weatherDB"
    }

}