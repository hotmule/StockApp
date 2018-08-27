package com.hotmule.stocksapp.models

import com.google.gson.annotations.Expose

class Stock(@Expose var name: String,
            @Expose var volume: Int,
            @Expose var price: Currency)