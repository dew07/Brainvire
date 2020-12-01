package com.example.brainvire.views.remote

import com.example.brainvire.model.ExchangeDTO
import com.example.brainvire.retrofitservices.networkutils.IDataSourceCallback
import kotlin.collections.ArrayList

interface DataSource {

    fun getList(callback: IDataSourceCallback<ArrayList<ExchangeDTO>>)

}