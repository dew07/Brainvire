package com.example.brainvire.views.listing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.brainvire.R
import com.example.brainvire.adapter.ListAdapter
import com.example.brainvire.databinding.ActivityListingBinding

class ListingActivity : AppCompatActivity() {

    lateinit var binding: ActivityListingBinding
    lateinit var viewModel: ListingViewModel
    lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_listing)
        viewModel = ViewModelProvider(this).get(ListingViewModel::class.java)

        listAdapter = ListAdapter(this)
        binding.recyclerView.adapter = listAdapter
        binding.viewModel = viewModel

        viewModel.getList()

        observers()
    }

    private fun observers() {
        viewModel.listLiveData.observe(this, Observer {
            listAdapter.list = it
        })
    }
}