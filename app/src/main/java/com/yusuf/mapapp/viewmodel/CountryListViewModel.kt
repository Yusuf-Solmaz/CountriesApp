package com.yusuf.mapapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusuf.mapapp.model.CountryModel

class CountryListViewModel : ViewModel() {

    val countries  = MutableLiveData<List<CountryModel>>()
    val error  = MutableLiveData<Boolean>()
    val countriesLoading  = MutableLiveData<Boolean>()
}