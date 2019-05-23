package com.exchangerates.infrascructure.di

import com.exchangerates.BuildConfig
import com.exchangerates.gateways.remote.ExchangeRatesApi
import com.exchangerates.utils.DateJsonAdapter
import com.squareup.moshi.Moshi
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private val retrofit = provideRetrofitInterface()

val apiModule = module {
    single { retrofit.create(ExchangeRatesApi::class.java) }
}

private fun provideRetrofitInterface(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API_URL)
        .addConverterFactory(MoshiConverterFactory.create(getCustomMoshi()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build()
}

private fun getCustomMoshi(): Moshi {
    return Moshi.Builder()
        .add(DateJsonAdapter())
        .build()
}
