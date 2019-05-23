package com.exchangerates.domain

import java.util.*

data class ExchangeRates(
    val base: String,
    private val rates: Map<String, Rate>,
    val date: Date
) {
    fun getRate(currency: String) = rates[currency]
}
