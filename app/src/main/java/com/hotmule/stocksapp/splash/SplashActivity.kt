package com.hotmule.stocksapp.splash

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.hotmule.stocksapp.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (savedInstanceState == null)
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fl_fragment_holder, SplashFragment())
                    .commit()
    }
}