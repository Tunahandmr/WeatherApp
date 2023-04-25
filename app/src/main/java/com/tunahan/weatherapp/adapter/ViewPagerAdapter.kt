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
    val glide: RequestManager
) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    private var myList: List<Int> = listOf(
        R.drawable.apple,
        R.drawable.strawberry,
        R.drawable.kiwi
    )
    var weatherList: List<String>? = null



    inner class ViewPagerViewHolder(val binding: ItemViewPagerBinding) :
        RecyclerView.ViewHolder(binding.root)

    /*  private val diffUtil = object : DiffUtil.ItemCallback<Weather>() {
          override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
              return oldItem == newItem
          }

          override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
              return oldItem == newItem
          }

      }


      private val diffUtil2 = object : DiffUtil.ItemCallback<WeatherResult>() {
          override fun areItemsTheSame(oldItem: WeatherResult, newItem: WeatherResult): Boolean {
              return oldItem == newItem
          }

          override fun areContentsTheSame(oldItem: WeatherResult, newItem: WeatherResult): Boolean {
              return oldItem == newItem
          }

      }

      private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)
      private val recyclerListDiffer2 = AsyncListDiffer(this, diffUtil2)

      var weatherList: List<Weather>
          get() = recyclerListDiffer.currentList
          set(value) = recyclerListDiffer.submitList(value)

      var weatherResult: List<WeatherResult>
          get() = recyclerListDiffer2.currentList
          set(value) = recyclerListDiffer2.submitList(value)
  */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = ItemViewPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        //   val curImage = weatherResult[position]
        //  val curList= myList[position]
        val wList = weatherList?.get(position)
        glide.load(wList).into(holder.binding.ivImage)


    }

}