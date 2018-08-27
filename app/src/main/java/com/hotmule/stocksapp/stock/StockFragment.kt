package com.hotmule.stocksapp.stock

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.hotmule.stocksapp.R
import com.hotmule.stocksapp.models.Stock
import com.hotmule.stocksapp.stock.domain.StockContract
import com.hotmule.stocksapp.stock.domain.StockPresenter
import kotlinx.android.synthetic.main.fragment_stock.*


class StockFragment : Fragment(), StockContract.View, Toolbar.OnMenuItemClickListener {

    private lateinit var presenter: StockContract.Presenter

    private lateinit var stockAdapter: StockAdapter

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private val delay: Long = 15000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        presenter = StockPresenter(this)
        stockAdapter = StockAdapter(mutableListOf())
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val toolbar = activity?.findViewById(R.id.tb_stock) as Toolbar
        toolbar.setOnMenuItemClickListener(this)

        return inflater.inflate(R.layout.fragment_stock, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_stock.adapter = stockAdapter
        rv_stock.layoutManager = LinearLayoutManager(activity)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        retainInstance = true
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.mn_refresh -> {
                stopTimer()
                startTimer()
                return true
            }
        }
        return false
    }

    override fun onResume() {
        super.onResume()
        startTimer()
    }

    override fun onPause() {
        super.onPause()
        stopTimer()
    }

    private fun startTimer() {
        presenter.getStock()

        handler = Handler()
        runnable = Runnable {
            presenter.getStock()
            handler.postDelayed(runnable, delay)
        }

        handler.postDelayed(runnable, delay)
    }

    private fun stopTimer() {
        handler.removeCallbacks(runnable)
    }

    override fun showStock(stockList: MutableList<Stock>) {
        stockAdapter.setStock(stockList)
    }

    override fun showConnectionErrorMessage() {
        stopTimer()
        Snackbar.make(cl_stock, R.string.no_connection, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.refresh) { startTimer() }
                .show()
    }

    override fun setProgressVisibility(isVisible: Boolean) {
        if (isVisible)
            progress_bar.visibility = View.VISIBLE
        else
            progress_bar.visibility = View.INVISIBLE
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}