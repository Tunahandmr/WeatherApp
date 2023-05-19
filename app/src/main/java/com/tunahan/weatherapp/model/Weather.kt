package com.tunahan.weatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weathers")
data class Weather(
    var city: String,
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
)