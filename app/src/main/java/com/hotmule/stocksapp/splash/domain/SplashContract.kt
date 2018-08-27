package com.hotmule.stocksapp.splash.domain

interface SplashContract {

    interface Presenter {
        fun checkInternetConnection()

        fun onDestroy()
    }

    interface View {
        fun openStocksPage()

        fun showConnectionErrorMessage()
    }
}