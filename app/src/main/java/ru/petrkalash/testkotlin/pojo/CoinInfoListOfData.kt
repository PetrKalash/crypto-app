package ru.petrkalash.testkotlin.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Список самых популярных валют
data class CoinInfoListOfData (
    // Мы получим данные из сети, и по ключу Data придет массив объектов Datum
    @SerializedName("Data")
    @Expose
    val data: List<Datum>? = null
)