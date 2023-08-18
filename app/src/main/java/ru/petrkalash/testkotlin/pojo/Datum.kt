package ru.petrkalash.testkotlin.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Datum в нашей реализации содержит только одно поле - информацию о валюте
data class Datum (
    @SerializedName("CoinInfo")
    @Expose
    val coinInfo: CoinInfo? = null
)