package com.yusuf.mapapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yusuf.mapapp.model.CountryModel
import com.yusuf.mapapp.service.CountryApiService
import com.yusuf.mapapp.service.CountryDAO
import com.yusuf.mapapp.service.CountryDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class CountryListViewModel(application: Application) : BaseViewModel(application) {

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
                    insertInSQLite(t)
               }

               override fun onError(e: Throwable) {
                   countriesLoading.value = false
                   error.value = true
                   println(e.message)
               }

           }))
    }

    private fun getCountries(t : List<CountryModel>){
        countries.value = t
        countriesLoading.value = false
        error.value = false
    }

    private fun insertInSQLite(list : List<CountryModel>){
             launch {
                  val dao = CountryDatabase(getApplication()).countryDao()
                  dao.deleteAllCountries()
                  val longList =  dao.insertAll(*list.toTypedArray()) //Listeyi teker teker döndürür
                  var i = 0
                  while (i< list.size){
                    list[i].id = longList[i].toInt()
                      i += 1
                    }
                 getCountries(list)
                 }
    }

}