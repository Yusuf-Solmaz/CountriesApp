package com.yusuf.mapapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yusuf.mapapp.R
import com.yusuf.mapapp.databinding.CountryRecyclerRowBinding
import com.yusuf.mapapp.model.CountryModel
import com.yusuf.mapapp.view.CountryList

class CountryAdapter(val countryList: ArrayList<CountryModel>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    class CountryHolder(val binding : CountryRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding = CountryRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
       holder.binding.countryName.text = countryList[position].countryName
        holder.binding.region.text = countryList[position].countryRegion
        //holder.binding.recyclerRowImage
    }

    fun updateCountry(newCountryList:List<CountryModel>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}