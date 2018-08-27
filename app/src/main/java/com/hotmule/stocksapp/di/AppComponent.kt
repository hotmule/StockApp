package com.hotmule.stocksapp.di

import com.hotmule.stocksapp.di.modules.StockModule
import com.hotmule.stocksapp.stock.domain.StockPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StockModule::class])
interface AppComponent {
    fun inject(stockPresenter: StockPresenter)
}