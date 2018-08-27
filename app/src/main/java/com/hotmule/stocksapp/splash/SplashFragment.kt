package com.hotmule.stocksapp.splash

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hotmule.stocksapp.R
import com.hotmule.stocksapp.splash.domain.SplashContract
import com.hotmule.stocksapp.splash.domain.SplashPresenter
import com.hotmule.stocksapp.stock.StockActivity
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment : Fragment(), SplashContract.View {

    private lateinit var presenter: SplashContract.Presenter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = SplashPresenter(this)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true
    }

    override fun onResume() {
        super.onResume()
        presenter.checkInternetConnection()
    }

    override fun openStocksPage() {
        startActivity(Intent(activity, StockActivity::class.java))
        activity?.finish()
    }

    override fun showConnectionErrorMessage() {
        Snackbar.make(cl_splash, R.string.no_connection, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.refresh) { presenter.checkInternetConnection() }
                .show()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}