package com.tunahan.weatherapp.repo

import androidx.lifecycle.LiveData
import com.tunahan.weatherapp.model.Weather
import com.tunahan.weatherapp.model.WeatherResult
import com.tunahan.weatherapp.util.Resource

interface WeatherRepositoryInterface {

    suspend fun insertWeather(weather: Weather)

    suspend fun updateWeather(weather: Weather)

    suspend fun deleteWeather(weather: Weather)

    fun getWeather(): LiveData<List<Weather>>

  //  suspend fun weatherCall(key:String,lang:String,city:String):Resource<WeatherResult>
}