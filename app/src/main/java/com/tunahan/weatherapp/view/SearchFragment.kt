package com.tunahan.weatherapp.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.tunahan.weatherapp.R
import com.tunahan.weatherapp.adapter.SearchAdapter
import com.tunahan.weatherapp.adapter.ViewPagerAdapter
import com.tunahan.weatherapp.databinding.FragmentSearchBinding
import com.tunahan.weatherapp.model.CityData
import com.tunahan.weatherapp.model.Weather
import com.tunahan.weatherapp.model.WeatherResult
import com.tunahan.weatherapp.service.WeatherAPI
import com.tunahan.weatherapp.util.Constans
import com.tunahan.weatherapp.viewmodel.WeatherViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class SearchFragment @Inject constructor(
    val glide: RequestManager
) : Fragment() {


    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchAdapter: SearchAdapter
    private var cityList = ArrayList<CityData>()
    lateinit var mWeatherViewModel: WeatherViewModel
    private var myL = kotlin.collections.ArrayList<Weather>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        mWeatherViewModel = ViewModelProvider(requireActivity())[WeatherViewModel::class.java]


        binding.searchRV.setHasFixedSize(true)
        binding.searchRV.layoutManager = LinearLayoutManager(requireContext())

        addDataToList()
        searchAdapter = SearchAdapter(cityList)
        binding.searchRV.adapter = searchAdapter

        searchAdapter.setOnItemClickListener(object : SearchAdapter.onItemClickListener {
            override fun onItemClick(city: String) {

                val weather = Weather(city, 0)
                mWeatherViewModel.insertWeather(weather)
                findNavController().navigate(R.id.action_searchFragment_to_mainFragment)

            }


        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        searchView()
        val a = "bursa"
        val weather = Weather(a,0)
        binding.tickButton.setOnClickListener {
            mWeatherViewModel.insertWeather(weather)
        }
     //  println(myL[0].city)


        binding.backButton.setOnClickListener {
            val action = SearchFragmentDirections.actionSearchFragmentToMainFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }


    private fun searchView() {

        //searchView
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<CityData>()
            for (i in cityList) {
                if (i.name.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(requireContext(), "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                searchAdapter.setData(filteredList)
            }
        }
    }

    private fun addDataToList() {

        cityList.add(CityData("adana"))
        cityList.add(CityData("adıyaman"))
        cityList.add(CityData("istanbul"))
        cityList.add(CityData("ankara"))
        cityList.add(CityData("izmir"))
        cityList.add(CityData("kocaeli"))
        cityList.add(CityData("bursa"))
        cityList.add(CityData("trabzon"))
        cityList.add(CityData("antalya"))
        cityList.add(CityData("mersin"))
        cityList.add(CityData("zonguldak"))


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}