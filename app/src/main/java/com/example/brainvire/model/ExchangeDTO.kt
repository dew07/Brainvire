package com.example.brainvire.model

class ExchangeDTO {
    var date: String ?= ""
    var exchangeCurrencyList = ArrayList<ExchangeCurrency>()
}

class ExchangeCurrency{
    var exchangeCurrency:String ?= ""
    var exchangeValue : Double ?= 0.0
}