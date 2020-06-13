package com.bardxhong.centralweatherbureau.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private const val CWB_OPEN_DATA_BASE_API_URL = "https://opendata.cwb.gov.tw/api/"
    val cwbService =
        Retrofit
            .Builder()
            .baseUrl(CWB_OPEN_DATA_BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(CWBService::class.java)
}