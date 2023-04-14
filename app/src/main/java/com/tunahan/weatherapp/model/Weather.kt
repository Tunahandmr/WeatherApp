package com.tunahan.weatherapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weathers")
data class Weather(
    @PrimaryKey(autoGenerate = true)
    val uid: Int=0,
    @ColumnInfo(name = "city")
    val city: String?,
    val date: String?,
    val day: String?,
    val icon: String?,
    val description: String?,
    val status: String?,
    val degree: String?,
    val min: String?,
    val max: String?,
    val night: String?,
    val humidity: String?
)