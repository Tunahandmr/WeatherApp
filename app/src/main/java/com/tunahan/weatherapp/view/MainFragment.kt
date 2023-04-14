package com.tunahan.weatherapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.tunahan.weatherapp.adapter.ViewPagerAdapter
import com.tunahan.weatherapp.databinding.FragmentMainBinding
import com.tunahan.weatherapp.model.WeatherResult
import com.tunahan.weatherapp.service.WeatherAPI
import com.tunahan.weatherapp.util.Constans
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class MainFragment @Inject constructor(
    val viewPagerAdapter: ViewPagerAdapter,
    val weatherAPI: WeatherAPI,
    val glide:RequestManager
): Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter= viewPagerAdapter
        binding.viewPager.adapter = adapter

        loadData()

    }

    private fun loadData() {

        /*  val client = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
              .readTimeout(30, TimeUnit.SECONDS)
              .addInterceptor { chain ->
                  val originalRequest = chain.request()
                  val requestWithHeaders = originalRequest.newBuilder()
                      .header("Content-Type", "application/json")
                      .build()
                  chain.proceed(requestWithHeaders)
              }
              .build()*/


        /* val retrofit = Retrofit.Builder()
             .baseUrl("https://api.collectapi.com/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()*/

        //   val service = retrofit.create(WeatherAPI::class.java)


        val call = weatherAPI.getWeather(
            authorization = Constans.API_KEY,
            lang = "tr",
            city = "ankara"
        )

        call.enqueue(object : Callback<WeatherResult> {
            override fun onResponse(call: Call<WeatherResult>, response: Response<WeatherResult>) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()
                   glide.load(weatherResponse?.result?.get(0)?.icon).into(binding.imageView)

                } else {
                    Log.e("WeatherService", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<WeatherResult>, t: Throwable) {
                Log.e("WeatherService", "Error: ${t.message}")
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}