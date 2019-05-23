package com.exchangerates.ui.home

import com.exchangerates.domain.ExchangeRates
import com.exchangerates.gateways.remote.callback.ApiCallback
import com.exchangerates.interactors.GetExchangeRates
import com.exchangerates.utils.formatToTime
import java.util.*

class HomePresenter(private val view: HomeContract.View,
                    private val getExchangeRates: GetExchangeRates) : HomeContract.Presenter {

    private var dataIsLoaded = false

    override fun onStart() {
        loadData()
    }

    override fun onFinish() {
        getExchangeRates.dispose()
    }

    override fun loadData() {
        if (!dataIsLoaded) {
            view.showData(false)
            view.showLoading(true)
        }
        getExchangeRates.execute(GetExchangeRatesApiCallback())
    }

    inner class GetExchangeRatesApiCallback: ApiCallback<ExchangeRates> {
        override fun onSuccess(result: ExchangeRates) {
            view.setExchangeRatesData(result, Date().formatToTime())
            if (!dataIsLoaded) {
                view.showData(true)
                view.showLoading(false)
                dataIsLoaded = true
            } else {
                view.showRefreshSuccess()
            }

            view.scheduleRefresh()
        }

        override fun onError() {
            if (!dataIsLoaded) {
                view.showLoading(false)
            }
            view.showRetryError {
                loadData()
            }
        }

        override fun onTerminate() {
            // nothing to do
        }
    }
}
