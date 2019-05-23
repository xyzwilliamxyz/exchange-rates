package com.exchangerates.infrascructure.di


import com.exchangerates.ui.home.HomeContract
import com.exchangerates.ui.home.HomePresenter
import org.koin.dsl.module

val presenterModule = module {
    factory<HomeContract.Presenter> { (view: HomeContract.View) ->
        HomePresenter(view, get())
    }
}
