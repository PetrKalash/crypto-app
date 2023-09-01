package ru.petrkalash.testkotlin.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import ru.petrkalash.testkotlin.api.ApiFactory.IMG_URL
import ru.petrkalash.testkotlin.utils.convertTimestampToTime

// Информация о валюте, в которую мы будем конвертировать
@Entity(tableName = "full_price_list")
data class CoinPriceInfo(
    @SerializedName("TYPE")
    @Expose
    val type: String?,
    @SerializedName("MARKET")
    @Expose
    val market: String?,
    @PrimaryKey
    @SerializedName("FROMSYMBOL")
    @Expose
    val fromSymbol: String,
    @SerializedName("TOSYMBOL")
    @Expose
    val toSymbol: String?,
    @SerializedName("PRICE")
    @Expose
    val price: String?,
    @SerializedName("LASTUPDATE")
    @Expose
    val lastUpdate: Long?,
    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeDay: String?,
    @SerializedName("VOLUME24HOUR")
    val highDay: String?,
    @SerializedName("LOWDAY")
    @Expose
    val lowDay: String?,
    @SerializedName("LASTMARKET")
    @Expose
    val lastMarket: String?,
    @SerializedName("IMAGEURL")
    @Expose
    val imageUrl: String?
) {
    fun getFullImageUrl(): String {
        return IMG_URL + imageUrl
    }
    fun getFormattedTime(): String {
        return convertTimestampToTime(lastUpdate)
    }
}