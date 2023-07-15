package com.yusuf.mapapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusuf.mapapp.model.CountryModel
import com.yusuf.mapapp.service.CountryApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CountryListViewModel : ViewModel() {

    val countries  = MutableLiveData<List<CountryModel>>()
    val error  = MutableLiveData<Boolean>()
    val countriesLoading  = MutableLiveData<Boolean>()
    var service = CountryApiService()
    val disposable = CompositeDisposable()

    fun refreshData(){
       getDataFromApi()
    }

   fun getDataFromApi(){
        countriesLoading.value = true
       disposable.add(service.getData()
           .subscribeOn(Schedulers.newThread())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribeWith(object : DisposableSingleObserver<List<CountryModel>>(){
               override fun onSuccess(t: List<CountryModel>) {
                   countries.value = t
                   countriesLoading.value = false
                   error.value = false
               }

               override fun onError(e: Throwable) {
                   countriesLoading.value = false
                   error.value = true
                   println(e.message)
               }

           }))




    }
}