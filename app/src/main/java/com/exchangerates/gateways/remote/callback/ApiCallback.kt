package com.exchangerates.gateways.remote.callback

interface ApiCallback<T> {

    fun onSuccess(result: T)

    fun onError()

    fun onTerminate()
}
