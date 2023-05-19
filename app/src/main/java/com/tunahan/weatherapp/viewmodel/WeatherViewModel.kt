package com.tunahan.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tunahan.weatherapp.adapter.SearchAdapter
import com.tunahan.weatherapp.model.CityData
import com.tunahan.weatherapp.model.Weather
import com.tunahan.weatherapp.model.WeatherResult
import com.tunahan.weatherapp.repo.WeatherRepositoryInterface
import com.tunahan.weatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repositoryInterface: WeatherRepositoryInterface,

) : ViewModel() {

    val weatherList = repositoryInterface.getWeather()

    private val weathers = MutableLiveData<Resource<WeatherResult>>()
    val weatherRetrofitList: LiveData<Resource<WeatherResult>>
        get() = weathers

    private var insertArtMsg = MutableLiveData<Resource<Weather>>()
    val insertArtMessage: LiveData<Resource<Weather>>
        get() = insertArtMsg

    fun resetInsertArtMsg() {
        insertArtMsg = MutableLiveData<Resource<Weather>>()
    }

    fun insertWeather(weather: Weather) = viewModelScope.launch {
        repositoryInterface.insertWeather(weather)
    }

    fun updateWeather(weather: Weather) = viewModelScope.launch {
        repositoryInterface.updateWeather(weather)
    }

    fun deleteWeather(weather: Weather) = viewModelScope.launch {
        repositoryInterface.deleteWeather(weather)
    }

    fun lowerCity(city: String): String {
        return city.lowercase()
    }

  /*  fun searchCity(key: String, lang: String, city: String) {
        if (city.isNotEmpty()) {
            weathers.value = Resource.loading(null)
            viewModelScope.launch {
                val response = repositoryInterface.weatherCall(key, lang, city)
                weathers.value = response

            }
        }
    }*/



}