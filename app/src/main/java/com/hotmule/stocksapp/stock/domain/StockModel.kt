package com.hotmule.stocksapp.stock.domain

import com.hotmule.stocksapp.app.StockApi

class StockModel(private var stockApi: StockApi) {

    fun getStock() = stockApi.getStock()
}