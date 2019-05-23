package com.exchangerates.ui.home

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.exchangerates.R
import com.exchangerates.domain.ExchangeRates
import com.exchangerates.utils.Constants
import com.exchangerates.utils.Constants.REFRESH_TIME
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class HomeActivity : AppCompatActivity(), HomeContract.View {

    private val presenter: HomeContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        presenter.onStart()
    }

    override fun setExchangeRatesData(exchangeRates: ExchangeRates, updatedAt: String) {
        tv_plnCurrency.text = exchangeRates.getRate(Constants.Currency.PLN)?.value.toString()
        tv_usdCurrency.text = exchangeRates.getRate(Constants.Currency.USD)?.value.toString()
        tv_lastUpdate.text = getString(R.string.lastUpdate, updatedAt)
    }

    override fun scheduleRefresh() {
        Handler().postDelayed({
            presenter.loadData()
        }, REFRESH_TIME)
    }

    override fun showRefreshSuccess() {
        val snackbar = Snackbar.make(cl_snackbarContainer, getString(R.string.refreshSuccessMessage), Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorSuccess))
        snackbar.show()
    }

    override fun showLoading(showHide: Boolean) {
        if (showHide) {
            pb_loading.visibility = View.VISIBLE
        } else {
            pb_loading.visibility = View.GONE
        }
    }

    override fun showData(showHide: Boolean) {
        if (showHide) {
            cl_container.visibility = View.VISIBLE
        } else {
            cl_container.visibility = View.GONE
        }
    }

    override fun showRetryError(retry: () -> Unit) {
        val snackbarContainer = if (cl_container.visibility == View.VISIBLE) {
            cl_snackbarContainer
        } else {
            cl_home
        }

        Snackbar.make(snackbarContainer, getString(R.string.errorMessage), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.errorRetry)) { retry() }.show()
    }
}
