package com.yusuf.mapapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusuf.mapapp.model.CountryModel

class CountryViewModel : ViewModel() {

    val countryModel = MutableLiveData<CountryModel>()

    fun getDataFromRoom(){
        val turkey = CountryModel("Turkey","Ankara","Europe","TR","","Turkish")
        countryModel.value = turkey
    }
}