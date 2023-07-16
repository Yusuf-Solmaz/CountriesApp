package com.yusuf.mapapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusuf.mapapp.model.CountryModel
import com.yusuf.mapapp.service.CountryDatabase
import kotlinx.coroutines.launch

class CountryViewModel(application: Application ) : BaseViewModel(application) {

    val countryLiveModel = MutableLiveData<CountryModel>()

    fun getDataFromRoom(id: Int){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val country = dao.getOneCountryById(id)
            countryLiveModel.value = country
        }
    }
}