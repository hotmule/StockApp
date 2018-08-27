package com.hotmule.stocksapp.stock.domain

import com.hotmule.stocksapp.app.StockApp
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription
import javax.inject.Inject

class StockPresenter(var view: StockContract.View) : StockContract.Presenter {

    @Inject
    lateinit var stockModel: StockModel

    init {
        StockApp.appComponent.inject(this)
    }

    var compositeSubscription: CompositeSubscription = CompositeSubscription()

    override fun getStock() {

        view.setProgressVisibility(true)

        val observable = stockModel.getStock()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ answer ->
                    view.showStock(answer.stock)
                    view.setProgressVisibility(false)
                }, {
                    view.showConnectionErrorMessage()
                    view.setProgressVisibility(false)
                })

        compositeSubscription.add(observable)

    }

    override fun onDestroy() {
        compositeSubscription.clear()
    }
}
