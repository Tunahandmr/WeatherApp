package com.tunahan.weatherapp.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.size
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.RequestManager
import com.tunahan.weatherapp.adapter.ViewPagerAdapter
import com.tunahan.weatherapp.databinding.FragmentMainBinding
import com.tunahan.weatherapp.databinding.ItemViewPagerBinding
import com.tunahan.weatherapp.model.Weather
import com.tunahan.weatherapp.model.WeatherResult
import com.tunahan.weatherapp.service.WeatherAPI
import com.tunahan.weatherapp.util.Constans
import com.tunahan.weatherapp.viewmodel.WeatherViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class MainFragment @Inject constructor(
    val viewPagerAdapter: ViewPagerAdapter,
    val weatherAPI: WeatherAPI,
    val glide: RequestManager
) : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    var job: Job? = null
    lateinit var mWeatherViewModel: WeatherViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onResume() {
        super.onResume()

        val myPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                Log.d("tunahan", position.toString())
            }
        }

        binding.viewPager.registerOnPageChangeCallback(myPageChangeCallback)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mWeatherViewModel = ViewModelProvider(requireActivity())[WeatherViewModel::class.java]
        mWeatherViewModel.weatherList.observe(viewLifecycleOwner, Observer { note ->
            viewPagerAdapter.setData(note)
        })


        callWeather()

        job?.cancel()
        job = lifecycleScope.launch {
            delay(1000)
            binding.viewPager.adapter = viewPagerAdapter
        }

        //  mWeatherViewModel.searchCity(Constans.API_KEY,"tr","ankara")

        binding.addIV.setOnClickListener {
            val a = MainFragmentDirections.actionMainFragmentToSearchFragment()
            Navigation.findNavController(it).navigate(a)
        }


    }


    private fun callWeather() {

        mWeatherViewModel.retrofitWeather(Constans.API_KEY,"tr","trabzon")

        mWeatherViewModel.myCall.observe(viewLifecycleOwner, Observer {
            it.enqueue(object : Callback<WeatherResult> {
                override fun onResponse(
                    call: Call<WeatherResult>,
                    response: Response<WeatherResult>
                ) {
                    if(response.isSuccessful){
                        val weatherResponse = response.body()
                        val v = weatherResponse?.result?.get(0)?.status.toString()
                        Log.d("response",v)
                    }

                }

                override fun onFailure(call: Call<WeatherResult>, t: Throwable) {
                    Log.e("WeatherService", "Error: ${t.message}")
                }

            })
        })
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}