package com.exchangerates.infrascructure

import android.app.Application
import com.exchangerates.infrascructure.di.apiModule
import com.exchangerates.infrascructure.di.interactorModule
import com.exchangerates.infrascructure.di.presenterModule

import org.koin.core.context.startKoin

class ExchangeRatesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(apiModule, interactorModule, presenterModule)
        }
    }
}
