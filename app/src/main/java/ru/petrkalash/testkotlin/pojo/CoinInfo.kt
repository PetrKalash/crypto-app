package ru.petrkalash.testkotlin.pojo

import android.media.Rating
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Информация о валюте
data class CoinInfo (
    @SerializedName("Id")
    @Expose
    private val id: String? = null,
    @SerializedName("Name")
    @Expose
    private val name: String? = null,
    @SerializedName("FullName")
    @Expose
    private val fullName: String? = null,
    @SerializedName("ImageUrl")
    @Expose
    private val imageUrl: String? = null
)