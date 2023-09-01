package ru.petrkalash.testkotlin.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.petrkalash.testkotlin.api.ApiFactory.IMG_URL

// Информация о валюте
data class CoinInfo (
    @SerializedName("Id")
    @Expose
    private val id: String? = null,
    @SerializedName("Name")
    @Expose
    val name: String? = null,
    @SerializedName("FullName")
    @Expose
    private val fullName: String? = null,
    @SerializedName("ImageUrl")
    @Expose
    val imageUrl: String? = null
)