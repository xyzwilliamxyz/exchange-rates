package com.exchangerates.gateways.remote.response

import java.util.*

data class ExchangeRatesResponse(
    val base: String,
    val rates: Map<String, Double>,
    val date: Date
)
