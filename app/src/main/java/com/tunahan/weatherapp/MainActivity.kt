package com.tunahan.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tunahan.weatherapp.databinding.ActivityMainBinding
import com.tunahan.weatherapp.model.WeatherResult
import com.tunahan.weatherapp.service.WeatherAPI
import com.tunahan.weatherapp.util.Constans.API_KEY
import com.tunahan.weatherapp.view.MainFragment_Factory
import com.tunahan.weatherapp.view.WeatherFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity: AppCompatActivity() {


    @Inject
    lateinit var fragmentFactory: WeatherFragmentFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)

    }

}