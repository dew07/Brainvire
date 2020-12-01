package com.example.brainvire.retrofitservices

import com.example.brainvire.retrofitservices.networkutils.retrofit
import com.example.brainvire.retrofitservices.services.ExchangeRatesService


object ExchangeRatesApi {
    val RETROFIT_SERVICE: ExchangeRatesService by lazy {
        retrofit.create(ExchangeRatesService::class.java)
    }
}

