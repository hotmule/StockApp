package com.hotmule.stocksapp.models

import com.google.gson.annotations.Expose

class StockList(@Expose var stock: MutableList<Stock>)