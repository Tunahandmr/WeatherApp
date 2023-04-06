package com.tunahan.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tunahan.weatherapp.databinding.ActivityMainBinding
import com.tunahan.weatherapp.model.WeatherResult
import com.tunahan.weatherapp.service.WeatherAPI
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()

    }

    private fun loadData() {

        val client = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestWithHeaders = originalRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .build()
                chain.proceed(requestWithHeaders)
            }
            .build()


        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.collectapi.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherAPI::class.java)


        val call = service.getWeather(
            authorization = "apikey 1ybYqv7X2Nmt5Nl3ziOAGg:5hwFbRtK8YR7yV0M4EFWNi",
            lang = "tr",
            city = "ankara"
        )

        call.enqueue(object : Callback<WeatherResult> {
            override fun onResponse(call: Call<WeatherResult>, response: Response<WeatherResult>) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()

                } else {
                    Log.e("WeatherService", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<WeatherResult>, t: Throwable) {
                Log.e("WeatherService", "Error: ${t.message}")
            }
        })
    }

}