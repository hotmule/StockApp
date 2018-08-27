package com.hotmule.stocksapp.splash.domain

import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.TimeUnit

class SplashPresenter(var view: SplashContract.View) : SplashContract.Presenter {

    var compositeSubscription: CompositeSubscription = CompositeSubscription()

    override fun checkInternetConnection() {
        val observable = hasInternetConnection()
                .delay(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { hasInternet ->
                    if (hasInternet)
                        view.openStocksPage()
                    else
                        view.showConnectionErrorMessage()
                }

        compositeSubscription.add(observable)
    }

    private fun hasInternetConnection(): Observable<Boolean> {
        return Observable.fromCallable {
            try {
                val timeoutMs = 1500
                val socket = Socket()
                val socketAddress = InetSocketAddress("8.8.8.8", 53)

                socket.connect(socketAddress, timeoutMs)
                socket.close()

                true
            } catch (e: IOException) {
                false
            }
        }
    }

    override fun onDestroy() {
        compositeSubscription.clear()
    }

}