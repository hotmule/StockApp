package com.hotmule.stocksapp.stock

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import com.hotmule.stocksapp.R
import kotlinx.android.synthetic.main.activity_stock.*

class StockActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)

        setSupportActionBar(tb_stock)

        if (savedInstanceState == null)
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fl_fragment_holder, StockFragment())
                    .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_stock, menu)
        return true
    }
}