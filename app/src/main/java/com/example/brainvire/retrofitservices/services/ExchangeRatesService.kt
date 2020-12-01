package com.example.brainvire.retrofitservices.services

import com.example.brainvire.utils.GET_EXCHANGE_RATES_LIST
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface ExchangeRatesService {

    @GET(GET_EXCHANGE_RATES_LIST)
    fun getList(
        @Query("start_at") startAt:String,
        @Query("end_at") endAt: String,
        @Query("base") base: String
    ): Call<ResponseBody>


}