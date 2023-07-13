package com.yusuf.mapapp.model

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName(value = "name")
    val countryName : String?,

    @SerializedName(value = "capital")
    val countryCapital:String?,

    @SerializedName(value = "region")
    val countryRegion: String?,

    @SerializedName(value = "currency")
    val countryCurrency: String?,

    @SerializedName(value = "flag")
    val countryFlag: String?,

    @SerializedName(value = "language")
    val countryLanguage: String?
)