package com.mdjdfd.mvvmhandsdirty.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mdjdfd.mvvmhandsdirty.R
import com.mdjdfd.mvvmhandsdirty.model.CurrencyModel
import kotlinx.android.synthetic.main.view_main_item.view.*

class MainAdapter(private val items: List<CurrencyModel>, val mListener: (String) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_main_item, parent, false)

        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
//        val item = items[position]
//        holder.textView.text = item.currency
//        holder.textView.setOnClickListener { listener(item.currency) }

        holder.bind(items[position], position, mListener)

    }

    override fun getItemCount(): Int = items.size

    class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(
            currencyModel: CurrencyModel,
            position: Int,
            mListener: (String) -> Unit
        ) {
            itemView.text_currency.text = currencyModel.currency
            itemView.text_price.text = currencyModel.price
        }
    }
}