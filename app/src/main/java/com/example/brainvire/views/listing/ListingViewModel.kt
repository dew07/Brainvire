package com.example.brainvire.views.listing

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.brainvire.model.ExchangeDTO
import com.example.brainvire.retrofitservices.networkutils.IDataSourceCallback
import com.example.brainvire.views.remote.Repository

class ListingViewModel(application: Application) : AndroidViewModel(application){

    private var repository: Repository = Repository.getInstance(application,true)!!
    var listLiveData = MutableLiveData<ArrayList<ExchangeDTO>>()

    var loading = MutableLiveData<String>("Loading")
    var loadingVisibility = ObservableBoolean(true)

    fun getList() {

        repository.getList(object : IDataSourceCallback<ArrayList<ExchangeDTO>> {

            override fun onDataFound(data: ArrayList<ExchangeDTO>) {
                listLiveData.value = data
                loadingVisibility.set(false)
            }

            override fun onDataNotFound() {
                Toast.makeText(getApplication(),"Data Not Found!",
                    Toast.LENGTH_SHORT).show()
                loadingVisibility.set(true)
                loading.value = "Data Not Found"

            }

            override fun onError(error: String) {
                Toast.makeText(getApplication(),error,
                    Toast.LENGTH_SHORT).show()
            }


        })

    }

}