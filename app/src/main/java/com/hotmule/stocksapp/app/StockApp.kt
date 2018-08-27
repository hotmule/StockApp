package com.hotmule.stocksapp.app

import android.app.Application
import com.hotmule.stocksapp.di.AppComponent
import com.hotmule.stocksapp.di.DaggerAppComponent

class StockApp : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
                .builder()
                .build()
    }

}