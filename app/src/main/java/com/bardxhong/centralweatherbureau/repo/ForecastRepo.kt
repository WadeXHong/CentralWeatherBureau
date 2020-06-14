package com.bardxhong.centralweatherbureau.repo

import com.bardxhong.centralweatherbureau.retrofit.Forecast
import com.bardxhong.centralweatherbureau.retrofit.RetrofitObject
import retrofit2.Response
import retrofit2.http.Query

class ForecastRepo {
    suspend fun get36HoursForecast(
        @Query("Authorization") token: String = "CWB-702B7697-51B0-4368-95FA-A9C69C6FCE7D",
        @Query("limit") limit: Int? = 100,
        @Query("offset") offset: Int? = 0,
        @Query("locationName") locationNames: List<String>? = null,
        @Query("elementName") elementNames: List<String>? = null
    ): Response<Forecast> =
        RetrofitObject.cwbService.get36HoursForecast(
            token = token,
            limit = limit,
            offset = offset,
            locationNames = locationNames?.joinToString(","),
            elementNames = elementNames?.joinToString(",")
        )
}