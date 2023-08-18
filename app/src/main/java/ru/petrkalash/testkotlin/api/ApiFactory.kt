package ru.petrkalash.testkotlin.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

// Создание синглтона для взаимодействия с интерфейсом ApiService
object ApiFactory {
    // Создаем объект Retrofit, который будет уметь реализовывать интерфейс ApiService
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://min-api.cryptocompare.com/data/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // При вызове apiService произойдет создание ретрофитом реализации интерфейса
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}