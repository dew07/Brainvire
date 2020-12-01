package com.example.brainvire.views.remote

import android.content.Context
import com.example.brainvire.model.ExchangeCurrency
import com.example.brainvire.model.ExchangeDTO
import com.example.brainvire.retrofitservices.networkutils.IDataSourceCallback
import com.example.brainvire.retrofitservices.NetworkController
import com.example.brainvire.retrofitservices.networkutils.NetworkResponseCallback
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class RemoteDataSource : DataSource {

    companion object {

        private var instance: RemoteDataSource? = null
        private var networkController: NetworkController? = null
        private var mContext: Context? = null

        @JvmStatic
        fun getInstance(context: Context?): DataSource? {
            mContext = context
            networkController = NetworkController.getInstance(context!!)
            if (instance == null) {
                instance = RemoteDataSource()
            }
            return instance
        }
    }


    override fun getList(
        callback: IDataSourceCallback<ArrayList<ExchangeDTO>>
    ) {
        networkController?.getList(object : NetworkResponseCallback {
            override fun onSuccess(data: String) {

                try {
                    val jsonObject = JSONObject(data)

                    if (jsonObject.has("rates")) {
                        val ratesJsonObject = jsonObject.getJSONObject("rates")
                        val exchangeArrayListDTO = ArrayList<ExchangeDTO>()
                        val iterator:Iterator<String> = ratesJsonObject.keys()
                        while (iterator.hasNext()){
                           val dateKey = iterator.next()
                            try {
                                val dateObject = ratesJsonObject.getJSONObject(dateKey)
                                val iteratorDateObjectKey:Iterator<String> = dateObject.keys()

                                val exchangeDTO = ExchangeDTO()
                                exchangeDTO.date = dateKey
                                val exchangeCurrencyList = ArrayList<ExchangeCurrency>()
                                while (iteratorDateObjectKey.hasNext()){
                                    val currencyKey = iteratorDateObjectKey.next()
                                    val exchangeValue = dateObject.get(currencyKey)
                                    val exchangeCurrency = ExchangeCurrency()
                                    exchangeCurrency.exchangeCurrency = currencyKey
                                    exchangeCurrency.exchangeValue = exchangeValue as Double?
                                    exchangeCurrencyList.add(exchangeCurrency)
                                }
                                exchangeDTO.exchangeCurrencyList = exchangeCurrencyList
                                exchangeArrayListDTO.add(exchangeDTO)
                            }catch (e:JSONException){
                                e.printStackTrace()
                            }
                        }
                        if(!exchangeArrayListDTO.isNullOrEmpty()){
                            callback.onDataFound(exchangeArrayListDTO)
                        }else{
                            callback.onDataNotFound()
                        }
                    }else{
                        callback.onError(NetworkController.SERVER_ERROR)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            override fun onError(errorCode: Int, errorData: String) {
                callback.onError(errorData)
            }

        })
    }


}