package com.exchangerates.gateways.converters

import com.exchangerates.domain.ExchangeRates
import com.exchangerates.domain.Rate
import com.exchangerates.gateways.remote.response.ExchangeRatesResponse

object ExchangeRatesConverter {

    fun fromResponse(response: Map<String, Double>): Map<String, Rate> {
        return mutableMapOf<String, Rate>().apply {
            response.entries.forEach {
                put(it.key, Rate(it.key, it.value))
            }
        }
    }

    fun fromResponse(response: ExchangeRatesResponse): ExchangeRates {
        return ExchangeRates(
            response.base,
            fromResponse(response.rates),
            response.date
        )
    }
}
