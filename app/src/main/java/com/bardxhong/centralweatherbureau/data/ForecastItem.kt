package com.bardxhong.centralweatherbureau.data

import com.bardxhong.centralweatherbureau.retrofit.IntervalData

data class ForecastItem(val data: IntervalData) : IItem

data class ImageItem(private val data: IntervalData) : IItem