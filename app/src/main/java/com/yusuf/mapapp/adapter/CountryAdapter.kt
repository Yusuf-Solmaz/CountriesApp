package com.yusuf.mapapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yusuf.mapapp.R
import com.yusuf.mapapp.databinding.CountryRecyclerRowBinding
import com.yusuf.mapapp.model.CountryModel
import com.yusuf.mapapp.util.downloadFromUrl
import com.yusuf.mapapp.util.placeHolder
import com.yusuf.mapapp.view.CountryListDirections

class CountryAdapter(val countryList: ArrayList<CountryModel>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>() {

    class CountryHolder(val binding : CountryRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        //val binding = CountryRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<CountryRecyclerRowBinding>(inflater, R.layout.country_recycler_row,parent,false)
        return CountryHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {

    holder.binding.country = countryList[position]


    /*holder.binding.countryName.text = countryList[position].countryName
        holder.binding.region.text = countryList[position].countryRegion
        holder.binding.recyclerRowImage.downloadFromUrl(countryList[position].countryFlag.toString(),
            placeHolder(holder.itemView.context)
        )

        holder.itemView.setOnClickListener {
            val action = CountryListDirections.actionCountryListToCountryInfo(countryList[position].id)
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    fun updateCountry(newCountryList:List<CountryModel>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }
}