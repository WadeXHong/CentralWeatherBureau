package com.bardxhong.centralweatherbureau.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CWBService {
    @GET("v1/rest/datastore/F-C0032-001")
    suspend fun get36HoursForecast(
        @Query("Authorization") token: String = "CWB-702B7697-51B0-4368-95FA-A9C69C6FCE7D",
        @Query("limit") limit: Int = 10,
        @Query("offset") offset: Int = 0,
        @Query("elementName") elementArray: Array<String>? = null
    ): Response<Forecast>
}

