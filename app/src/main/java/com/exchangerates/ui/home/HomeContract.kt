package com.exchangerates.ui.home

import com.exchangerates.BasePresenter
import com.exchangerates.BaseView
import com.exchangerates.domain.ExchangeRates

interface HomeContract {

    interface Presenter: BasePresenter {
        fun loadData()
    }

    interface View: BaseView {
        fun setExchangeRatesData(exchangeRates: ExchangeRates, updatedAt: String)

        fun scheduleRefresh()

        fun showRefreshSuccess()

        fun showData(showHide: Boolean)

        fun showRetryError(retry: () -> Unit)
    }
}
