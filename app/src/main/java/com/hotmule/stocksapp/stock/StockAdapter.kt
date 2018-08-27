package com.hotmule.stocksapp.stock

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hotmule.stocksapp.R
import com.hotmule.stocksapp.models.Stock
import java.math.BigDecimal
import java.math.RoundingMode

class StockAdapter(var stockList: MutableList<Stock>) : RecyclerView.Adapter<StockAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_row, parent, false))
    }

    override fun getItemCount(): Int = stockList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val stock = stockList[position]

        holder.tvName.text = stock.name
        holder.tvVolume.text = stock.volume.toString()
        holder.tvAmount.text = (BigDecimal(stock.price.amount)
                .setScale(2, RoundingMode.UP)
                .toDouble()).toString()
    }

    fun setStock(stockList: MutableList<Stock>) {
        this.stockList = stockList
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvVolume: TextView = itemView.findViewById(R.id.tv_volume)
        var tvAmount: TextView = itemView.findViewById(R.id.tv_amount)
    }
}