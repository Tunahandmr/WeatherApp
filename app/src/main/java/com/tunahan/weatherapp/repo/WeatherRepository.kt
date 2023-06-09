package com.tunahan.weatherapp.repo

import androidx.lifecycle.LiveData
import com.tunahan.weatherapp.model.Weather
import com.tunahan.weatherapp.model.WeatherResult
import com.tunahan.weatherapp.roomdb.WeatherDao
import com.tunahan.weatherapp.service.WeatherAPI
import com.tunahan.weatherapp.util.Resource
import retrofit2.Call
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherDao: WeatherDao,
    private val weatherAPI: WeatherAPI
): WeatherRepositoryInterface {

    override suspend fun insertWeather(weather: Weather) {
        weatherDao.insertWeather(weather)
    }

    override suspend fun updateWeather(weather: Weather) {
        weatherDao.updateWeather(weather)
    }

    override suspend fun deleteWeather(weather: Weather) {
        weatherDao.deleteWeather(weather)
    }

    override fun getWeather(): LiveData<List<Weather>> {
        return weatherDao.getAllWeather()
    }

    override fun retrofitWeather(
        authorization: String,
        lang: String,
        city: String
    ): Call<WeatherResult> {
        return weatherAPI.getWeather(authorization, lang, city)
    }
/*
    override suspend fun weatherCall(
        key: String,
        lang: String,
        city: String
    ): Resource<WeatherResult> {
        return try {
            val response = weatherAPI.getWeather(key,lang, city)
            if (response.isSuccessful){
                response.body()?.let {
                    return Resource.success(it)
                }?: Resource.error("Error",null)
            }else{
                Resource.error("Error",null)
            }
        }catch (e: Exception) {
            Resource.error("No data!",null)
        }
    }*/
}