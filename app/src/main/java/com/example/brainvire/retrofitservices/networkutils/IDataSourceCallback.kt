package com.example.brainvire.retrofitservices.networkutils

interface IDataSourceCallback<T> {
    fun onDataFound(data: T) {}
    fun onDataFound(data: T, responseCode: Int) {}
    fun onDataNotFound() {}
    fun onError(error: String)
}