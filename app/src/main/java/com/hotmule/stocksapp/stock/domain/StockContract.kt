package com.hotmule.stocksapp.stock.domain

import com.hotmule.stocksapp.models.Stock

interface StockContract {

    interface Presenter {
        fun getStock()

        fun onDestroy()
    }

    interface View {
        fun showStock(stockList: MutableList<Stock>)

        fun showConnectionErrorMessage()

        fun setProgressVisibility(isVisible: Boolean)
    }
}