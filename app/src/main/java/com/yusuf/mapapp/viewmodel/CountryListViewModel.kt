package com.yusuf.mapapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusuf.mapapp.model.CountryModel

class CountryListViewModel : ViewModel() {

    val countries  = MutableLiveData<List<CountryModel>>()
    val error  = MutableLiveData<Boolean>()
    val countriesLoading  = MutableLiveData<Boolean>()

    fun refreshData(){
        val turkey = CountryModel("Turkey","Ankara","Europe","TR","","Turkish")
        val italy = CountryModel("Italy","Rome","Europe","EUR","","Italian")
        val usa = CountryModel("USA","Washington","America","USD","","English")
        val spain = CountryModel("Spain","Madrid","Europe","EUR","","Spanish")

        val countryList = arrayListOf<CountryModel>(turkey,italy,usa,spain)

        countries.value = countryList
        error.value = false
        countriesLoading.value = false
    }
}