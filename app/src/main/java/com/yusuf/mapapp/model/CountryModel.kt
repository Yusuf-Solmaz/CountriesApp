package com.yusuf.mapapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity("country")
data class CountryModel(

    @ColumnInfo("name")
    @SerializedName(value = "name")
    val countryName : String?,

    @ColumnInfo("capital")
    @SerializedName(value = "capital")
    val countryCapital:String?,

    @ColumnInfo("region")
    @SerializedName(value = "region")
    val countryRegion: String?,

    @ColumnInfo("currency")
    @SerializedName(value = "currency")
    val countryCurrency: String?,

    @ColumnInfo("flag")
    @SerializedName(value = "flag")
    val countryFlag: String?,

    @ColumnInfo("language")
    @SerializedName(value = "language")
    val countryLanguage: String?
)

{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}