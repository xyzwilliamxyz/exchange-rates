package com.exchangerates.gateways.remote

import com.exchangerates.gateways.remote.response.ExchangeRatesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ExchangeRatesApi {

    @GET("latest")
    fun getLatest(): Observable<ExchangeRatesResponse>
}
