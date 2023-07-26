package ru.petrkalash.testkotlin.pojo

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Массив данных валют
data class CoinPriceInfoRawData (
    @SerializedName("RAW")
    @Expose
    private val coinPriceInfJsonObject: JsonObject? = null
)