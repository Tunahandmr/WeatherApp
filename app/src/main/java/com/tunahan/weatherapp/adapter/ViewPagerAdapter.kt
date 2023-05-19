package com.tunahan.weatherapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.tunahan.weatherapp.R
import com.tunahan.weatherapp.databinding.ItemViewPagerBinding
import com.tunahan.weatherapp.model.Weather
import com.tunahan.weatherapp.model.WeatherResult
import com.tunahan.weatherapp.service.WeatherAPI
import com.tunahan.weatherapp.util.Constans
import com.tunahan.weatherapp.view.MainFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ViewPagerAdapter @Inject constructor(
    val weatherAPI: WeatherAPI,
    val glide: RequestManager
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ItemViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val diffUtil = object : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }

    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)


    var weatherList: List<Weather>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        if (weatherList.isNotEmpty()) {
            val wList = weatherList[position]


            val call = weatherAPI.getWeather(
                authorization = Constans.API_KEY,
                lang = "tr",
                city = wList.city
            )

            call.enqueue(object : Callback<WeatherResult> {
                override fun onResponse(
                    call: Call<WeatherResult>,
                    response: Response<WeatherResult>
                ) {
                    if (response.isSuccessful) {
                        val weatherResponse = response.body()
                        val city = wList.city
                        val degree = weatherResponse?.result?.get(0)?.degree.toString()
                        val description = weatherResponse?.result?.get(0)?.description.toString()

                        val date = weatherResponse?.result?.get(1)?.date.toString()
                        val day = weatherResponse?.result?.get(1)?.day.toString()
                        val icon = weatherResponse?.result?.get(1)?.icon.toString()
                        val min = weatherResponse?.result?.get(1)?.min.toString()
                        val max = weatherResponse?.result?.get(1)?.max.toString()

                        val date2 = weatherResponse?.result?.get(2)?.date.toString()
                        val day2 = weatherResponse?.result?.get(2)?.day.toString()
                        val icon2 = weatherResponse?.result?.get(2)?.icon.toString()
                        val min2 = weatherResponse?.result?.get(2)?.min.toString()
                        val max2 = weatherResponse?.result?.get(2)?.max.toString()

                        val date3 = weatherResponse?.result?.get(3)?.date.toString()
                        val day3 = weatherResponse?.result?.get(3)?.day.toString()
                        val icon3 = weatherResponse?.result?.get(3)?.icon.toString()
                        val min3 = weatherResponse?.result?.get(3)?.min.toString()
                        val max3 = weatherResponse?.result?.get(3)?.max.toString()

                        holder.binding.cityTV.text = city
                        glide.load(icon).into(holder.binding.ivImage)
                        holder.binding.degreeTV.text = "$degree °C"
                        holder.binding.statusTV.text = description

                        holder.binding.date1.text = date
                        holder.binding.day1.text = day
                        holder.binding.mindegree1.text = "$min °"
                        holder.binding.maxdegree1.text = "$max °"
                        glide.load(icon).into(holder.binding.image1)

                        holder.binding.date2.text = date2
                        holder.binding.day2.text = day2
                        holder.binding.mindegree2.text = "$min2 °"
                        holder.binding.maxdegree2.text = "$max2 °"
                        glide.load(icon2).into(holder.binding.image2)

                        holder.binding.date3.text = date3
                        holder.binding.day3.text = day3
                        holder.binding.mindegree3.text = "$min3 °"
                        holder.binding.maxdegree3.text = "$max3 °"
                        glide.load(icon3).into(holder.binding.image3)

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


    fun setData(note: List<Weather>) {
        weatherList = note
        notifyDataSetChanged()

    }

}