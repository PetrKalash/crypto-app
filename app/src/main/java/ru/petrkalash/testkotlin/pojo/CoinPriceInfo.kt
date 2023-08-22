package ru.petrkalash.testkotlin.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
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
    val fromsymbol: String,

    @SerializedName("TOSYMBOL")
    @Expose
    val tosymbol: String?,

    @SerializedName("FLAGS")
    @Expose
    val flags: String?,

    @SerializedName("PRICE")
    @Expose
    val price: Double?,

    @SerializedName("LASTUPDATE")
    @Expose
    val lastupdate: Int?,

    @SerializedName("LASTVOLUME")
    @Expose
    val lastvolume: Double?,

    @SerializedName("VOLUMEDAY")
    @Expose
    val volumeday: Double?,

    @SerializedName("VOLUME24HOUR")
    @Expose
    val volume24hour: Double?,
    ) {
    fun getFormattedTime(): String {
        return convertTimestampToTime(lastupdate)
    }
}