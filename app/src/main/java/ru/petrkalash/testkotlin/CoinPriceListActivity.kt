package ru.petrkalash.testkotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.petrkalash.testkotlin.adapters.CoinInfoAdapter
import ru.petrkalash.testkotlin.databinding.ActivityCoinPriceListBinding

class CoinPriceListActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityCoinPriceListBinding
    // Создаем ссылку на ViewModel
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)

        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = adapter

        // Создание и запуск ViewModel
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        // Топ валют
        viewModel.priceList.observe(this) {
            // Взаимодействие с адаптером
            adapter.coinInfoList = it
            Log.d("TEST_COINS", "Success in Activity: $it")
        }
        // Информация об одной валюте
        viewModel.getDetailInfo("BTC").observe(this) {
            Log.d("TEST_COINS", "Success in Activity: $it")
        }
    }
}