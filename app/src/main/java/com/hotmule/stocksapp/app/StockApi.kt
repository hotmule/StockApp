package com.hotmule.stocksapp.app

import com.hotmule.stocksapp.models.StockList
import retrofit2.http.GET
import rx.Single

interface StockApi {
    companion object {
        const val BASE_URL = "http://phisix-api3.appspot.com/"
    }

    @GET("stocks.json")
    fun getStock() : Single<StockList>
}