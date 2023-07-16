package com.yusuf.mapapp.service

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.yusuf.mapapp.model.CountryModel

@Dao
interface CountryDAO {

    @Insert
    suspend fun insertAll(vararg countries : CountryModel): List<Long>

    @Query("Select * from country ")
    suspend fun getAllCountries (): List<CountryModel>

    @Query("Select * from country where id =:id")
    suspend fun getOneCountryById (id: Int) : CountryModel

    @Delete
    suspend fun deleteCountry(country: CountryModel)

    @Query("Delete from country where name= :name")
    suspend fun deleteCountryByName(name : String)

    @Query("Delete from country")
    suspend fun deleteAllCountries()
}