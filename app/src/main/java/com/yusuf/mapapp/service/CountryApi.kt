package com.yusuf.mapapp.service

import com.yusuf.mapapp.model.CountryModel
import io.reactivex.Single
import retrofit2.http.GET

interface CountryApi {

    // https://raw.githubusercontent.com/
    // Yusuf-Solmaz/MyData/main/Countries.json

    @GET("Yusuf-Solmaz/MyData/main/Countries.json")
    fun getCountries(): Single<List<CountryModel>>


}