package com.example.brainvire.retrofitservices.networkutils

interface NetworkResponseCallback {

    fun onSuccess(data:String)

    fun onError(errorCode : Int, errorData : String)
}