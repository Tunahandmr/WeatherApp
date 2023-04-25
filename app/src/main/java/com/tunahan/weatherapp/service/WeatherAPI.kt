package com.tunahan.weatherapp.service

import com.tunahan.weatherapp.model.WeatherResult
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherAPI {

    @GET("weather/getWeather")
    fun getWeather(@Header("Authorization") authorization: String,@Query("data.lang") lang: String, @Query("data.city") city: String): Response<WeatherResult>



}