package ru.petrkalash.testkotlin

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.petrkalash.testkotlin.api.ApiFactory
import ru.petrkalash.testkotlin.database.AppDatabase
import ru.petrkalash.testkotlin.pojo.CoinPriceInfo
import ru.petrkalash.testkotlin.pojo.CoinPriceInfoRawData
import java.util.concurrent.TimeUnit

class CoinViewModel(application: Application) : AndroidViewModel(application) {
    // Экземпляр базы данных
    private val db = AppDatabase.getInstance(application);
    // Хранение всех одноразовых файлов в одном месте
    private val compositeDisposable = CompositeDisposable();

    // Вывод ТОП самых популярных валют на данный момент
    val priceList = db.coinPriceInfoDao().getPriceList()
    // Подробная информация об одной валюте (в качестве параметра
    // принимает валюту, о которой мы хотим получить информацию)
    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> {
        return db.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)
    }

    // Автоматическая загрузка данных (блок инициализации)
    init {
        loadData()
    }

    // Загрузка данных из сети
    private fun loadData() {
        // Получаем ТОП самых популярных валют
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 5)
            // Превращаем список самых популярных валют в одну строку (имена валют через запятую)
            .map { it.data?.map { its -> its.coinInfo?.name }?.joinToString(",").toString() }
            // Загрузка всей информации о валютах (в качестве параметра - полученная в map строка)
            .flatMap { ApiFactory.apiService.getFullPriceList(fsyms = it) }
            // Преобразовываем RawData в ListPriceInfo
            .map { getPriceListFromRawData(it) }
            .delaySubscription(2, TimeUnit.SECONDS)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                db.coinPriceInfoDao().insertPriceList(it);
                Log.d("TEST_COINS", "Success: $it");
            }, {
                Log.d("TEST_COINS", "Failure: ${it.message}")
            })
        compositeDisposable.add(disposable)
    }

    // Приведение объекта в нужную коллекцию (вручную парсим JSON)
    private fun getPriceListFromRawData(coinPriceInfoRawData: CoinPriceInfoRawData): List<CoinPriceInfo> {
        // Коллекция, хранящая информацию о валютах
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        // Получаем все ключи JSON-объекта
        val coinKeys = jsonObject.keySet()
        // Проходимся по всем ключам и получаем вложенные JSON-объекты
        for (coinKey in coinKeys) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            // Получаем новые ключи внутри ключей из JSON-объектов
            val currencyKeys = currencyJson.keySet()
            for (currencyKey in currencyKeys) {
                // Конвертируем полученный ключ в CoinPriceInfo
                val priceInfo = Gson().fromJson(currencyJson.getAsJsonObject(currencyKey), CoinPriceInfo::class.java)
                // Добавляем полученный элемент в коллекцию
                result.add(priceInfo)
            }
        }
        // Возвращаем результат - коллекцию
        return result
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}