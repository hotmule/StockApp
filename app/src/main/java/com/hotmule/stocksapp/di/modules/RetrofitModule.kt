package com.hotmule.stocksapp.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hotmule.stocksapp.app.StockApi
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.schedulers.Schedulers
import javax.inject.Singleton

@Module
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(builder: Retrofit.Builder) : Retrofit
            = builder.baseUrl(StockApi.BASE_URL).build()

    @Provides
    @Singleton
    fun provideRetrofitBuilder(converterFactory: Converter.Factory) : Retrofit.Builder
            = Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(converterFactory)

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
}