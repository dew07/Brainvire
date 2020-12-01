package com.example.brainvire.retrofitservices.networkutils

import com.example.brainvire.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.concurrent.TimeUnit

const val TIMEOUT = 60 * 1000L


val client: OkHttpClient = OkHttpClient().newBuilder()
    .connectTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(TIMEOUT, TimeUnit.SECONDS).writeTimeout(
        TIMEOUT, TimeUnit.SECONDS)
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .client(client)
    .baseUrl(BASE_URL)
    .build()


fun getStringResponseFromRaw(response: ResponseBody): String? {
    var reader: BufferedReader?
    val sb = java.lang.StringBuilder()
    try {
        reader = BufferedReader(InputStreamReader(response.byteStream()))
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                sb.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return sb.toString()
}


fun getStringResponseFromRaw(response: Response<ResponseBody>): String? {
    var reader: BufferedReader? = null
    val sb = java.lang.StringBuilder()
    try {
        reader = BufferedReader(InputStreamReader(response.body()!!.byteStream()))
        var line: String?
        try {
            while (reader.readLine().also { line = it } != null) {
                sb.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
    return sb.toString()
}

