package com.tunahan.weatherapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.tunahan.weatherapp.adapter.ViewPagerAdapter
import com.tunahan.weatherapp.service.WeatherAPI
import javax.inject.Inject

class WeatherFragmentFactory @Inject
constructor(
    private val viewPagerAdapter: ViewPagerAdapter,
    private val glide: RequestManager,
    private val weatherAPI: WeatherAPI

): FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            MainFragment::class.java.name -> MainFragment(viewPagerAdapter,weatherAPI,glide)
            SearchFragment::class.java.name -> SearchFragment(glide)
            else->super.instantiate(classLoader, className)
        }

    }
}