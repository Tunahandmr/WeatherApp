package com.tunahan.weatherapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tunahan.weatherapp.databinding.RecyclerRowBinding
import com.tunahan.weatherapp.model.CityData
import javax.inject.Inject

class SearchAdapter @Inject constructor(
    var mList: List<CityData>
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {


    private lateinit var mListener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(city: String)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {

        mListener = listener
    }

    class SearchViewHolder(val binding: RecyclerRowBinding, listener: onItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.searchText.setOnClickListener {
                listener.onItemClick(binding.searchText.text.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(view,mListener)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val currentCity = mList[position]
        holder.binding.searchText.text = currentCity.name
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(mList: List<CityData>) {
        this.mList = mList
        notifyDataSetChanged()
    }
}