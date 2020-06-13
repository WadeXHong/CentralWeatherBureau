package com.bardxhong.centralweatherbureau

import com.bardxhong.centralweatherbureau.retrofit.Forecast
import com.bardxhong.centralweatherbureau.retrofit.RetrofitObject
import retrofit2.Response

class ForecastRepo {
    suspend fun get36HoursForecast(): Response<Forecast> = RetrofitObject.cwbService.get36HoursForecast()
}