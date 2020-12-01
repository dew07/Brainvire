package com.example.brainvire.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brainvire.databinding.MainCellLayoutBinding
import com.example.brainvire.databinding.SubCellLayoutBinding
import com.example.brainvire.model.ExchangeCurrency
import com.example.brainvire.model.ExchangeDTO

class SubListAdapter(
    context: Context,
    exchangeCurrencyList: ArrayList<ExchangeCurrency>
) :
    RecyclerView.Adapter<SubListAdapter.ViewHolder>(){

    var context:Context ?=null
    var exchangeCurrencyList =ArrayList<ExchangeCurrency>()
    init {
        this.context = context
        this.exchangeCurrencyList = exchangeCurrencyList
    }

    class ViewHolder(val binding: SubCellLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(
            exchangeCurrency: ExchangeCurrency
        ) {
            binding.exchangeCurrency = exchangeCurrency

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SubCellLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
       return exchangeCurrencyList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = exchangeCurrencyList[position]
        holder.bind(item)
    }

}