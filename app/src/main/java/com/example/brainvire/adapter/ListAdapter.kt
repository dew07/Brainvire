package com.example.brainvire.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brainvire.databinding.MainCellLayoutBinding
import com.example.brainvire.model.ExchangeCurrency
import com.example.brainvire.model.ExchangeDTO

class ListAdapter(private var context: Context
) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    var list = ArrayList<ExchangeDTO>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val binding: MainCellLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(
            exchangeDTO: ExchangeDTO,
            context: Context
        ) {

            binding.exchangeDTO = exchangeDTO

            setGameDetailsRecyclerView(context, binding.recyclerView,exchangeDTO.exchangeCurrencyList)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MainCellLayoutBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }

            private fun setGameDetailsRecyclerView(
                context: Context,
                recyclerView: RecyclerView,
                exchangeCurrencyList: ArrayList<ExchangeCurrency>
            ) {
                val subListAdapter = SubListAdapter(context,exchangeCurrencyList)
                recyclerView.adapter = subListAdapter
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item,context)
    }

}