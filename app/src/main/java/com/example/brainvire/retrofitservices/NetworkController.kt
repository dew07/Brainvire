package com.example.brainvire.retrofitservices

import android.content.Context
import com.example.brainvire.retrofitservices.networkutils.NetworkResponseCallback
import com.example.brainvire.retrofitservices.networkutils.getStringResponseFromRaw
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class NetworkController {

    companion object {
        var instance: NetworkController? = null
        lateinit var mContext: Context

        const val SERVER_ERROR = "Something went wrong on the server"

        fun getInstance(context: Context): NetworkController {
            mContext = context

            if (instance == null) {
                instance = NetworkController()
            }
            return instance as NetworkController
        }

    }

    private class RetrofitServiceTask(var networkResponseCallback: NetworkResponseCallback) :
        Callback<ResponseBody> {

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            if (response.code() == 200) {
                getStringResponseFromRaw(response)?.let { networkResponseCallback.onSuccess(it) }
            } else {
                val jsonError = getStringResponseFromRaw(response.errorBody()!!)
                try {
                    val jsonObject = JSONObject(jsonError.toString())
                    networkResponseCallback.onSuccess(jsonObject.toString())
                } catch (e: Exception) {
                    networkResponseCallback.onError(response.code(), SERVER_ERROR)
                }
            }
        }


        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            networkResponseCallback.onError(0, t.message.toString())
        }

    }

    fun getList(callback: NetworkResponseCallback) {
        val response = ExchangeRatesApi.RETROFIT_SERVICE.getList("2018-01-01","2018-06-01","USD")
        val responseCall: Call<ResponseBody> = response
        responseCall.enqueue(RetrofitServiceTask(callback))
    }

}