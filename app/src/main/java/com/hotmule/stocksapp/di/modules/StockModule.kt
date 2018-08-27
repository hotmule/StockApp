package com.hotmule.stocksapp.di.modules

import com.hotmule.stocksapp.app.StockApi
import com.hotmule.stocksapp.stock.domain.StockModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class StockModule {
    @Provides
    @Singleton
    fun provideStockModel(stockApi: StockApi) : StockModel = StockModel(stockApi)
}