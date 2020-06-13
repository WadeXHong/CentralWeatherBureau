package com.bardxhong.centralweatherbureau.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CWBService {
    @GET("v1/rest/datastore/F-C0032-001")
    suspend fun get36HoursForecast(
        @Query("Authorization") token: String,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("locationName") locationNames: String? = null,
        @Query("elementName") elementNames: String? = null
    ): Response<Forecast>
}

