package com.exchangerates.interactors

import com.exchangerates.gateways.converters.ExchangeRatesConverter
import com.exchangerates.gateways.remote.ExchangeRatesApi
import com.exchangerates.ui.home.HomePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GetExchangeRates constructor(private val exchangeRatesApi: ExchangeRatesApi) {

    private val disposables = CompositeDisposable()

    fun execute(callback: HomePresenter.GetExchangeRatesApiCallback) {
        disposables.add(
            exchangeRatesApi.getLatest()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { callback.onTerminate() }
                .map { result -> ExchangeRatesConverter.fromResponse(result) }
                .subscribe(
                    { result -> callback.onSuccess(result) },
                    { callback.onError() }
                )
        )
    }

    fun dispose() {
        disposables.dispose()
    }
}
