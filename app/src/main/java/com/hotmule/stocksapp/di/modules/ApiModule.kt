package com.hotmule.stocksapp.di.modules

import com.hotmule.stocksapp.app.StockApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RetrofitModule::class])
class ApiModule {
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) : StockApi = retrofit.create(StockApi::class.java)
}