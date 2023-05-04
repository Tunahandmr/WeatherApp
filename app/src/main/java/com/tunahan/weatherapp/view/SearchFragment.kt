package com.tunahan.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunahan.weatherapp.adapter.SearchAdapter
import com.tunahan.weatherapp.databinding.FragmentSearchBinding
import com.tunahan.weatherapp.model.CityData
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class SearchFragment @Inject constructor(

) : Fragment() {


    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var searchAdapter: SearchAdapter
    private var cityList = ArrayList<CityData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.searchRV.setHasFixedSize(true)
        binding.searchRV.layoutManager = LinearLayoutManager(requireContext())

        addDataToList()
        searchAdapter = SearchAdapter(cityList)
        binding.searchRV.adapter = searchAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchView()

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