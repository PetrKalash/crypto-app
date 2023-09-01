package ru.petrkalash.testkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.petrkalash.testkotlin.databinding.ActivityCoinDetailBinding

class CoinDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoinDetailBinding

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)

        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) { return; }
        val fromSymbol: String = intent.getStringExtra(EXTRA_FROM_SYMBOL).toString();

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        // Информация об одной валюте
        viewModel.getDetailInfo(fromSymbol).observe(this) {
            Picasso.get().load(it.getFullImageUrl()).into(binding.ivLogoCoin)
            binding.tvFromSymbol.text = it.fromSymbol
            binding.tvToSymbol.text = it.toSymbol
            binding.tvPrice.text = it.price
            binding.tvMinPrice.text = it.lowDay
            binding.tvMaxPrice.text = it.highDay
            binding.tvLastMarker.text = it.lastMarket
            binding.tvUpdate.text = it.getFormattedTime()
        }
    }

    // Инкапсуляция ключей
    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym";

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}