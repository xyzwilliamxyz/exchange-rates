package com.exchangerates.infrascructure.di

import com.exchangerates.interactors.GetExchangeRates
import org.koin.dsl.module

val interactorModule = module {
    factory { GetExchangeRates(get()) }
}
