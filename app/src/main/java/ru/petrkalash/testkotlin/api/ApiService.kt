package ru.petrkalash.testkotlin.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.petrkalash.testkotlin.pojo.CoinInfoListOfData
import ru.petrkalash.testkotlin.pojo.CoinPriceInfoRawData

interface ApiService {
    // Получение списка самых популярных валют (вставляем эндпоинт и передаем параметры)
    @GET("top/totalvolfull")
    fun getTopCoinsInfo(
        // Устанавливаем значения по-умолчанию
        @Query("apiKey") apiKey: String = "",
        @Query("limit") limit: Int = 10,
        @Query("tsym") tsym: String = "USD"
    ): Single<CoinInfoListOfData>

    @GET("pricemultifull")
    fun getFullPriceList(
        @Query("apiKey") apiKey: String = "",
        @Query("fsyms") fsyms: String = "BTC",
        @Query("tsyms") tsyms: String = "USD"
    ): Single<CoinPriceInfoRawData>
}