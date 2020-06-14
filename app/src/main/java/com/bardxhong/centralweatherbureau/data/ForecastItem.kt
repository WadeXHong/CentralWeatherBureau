package com.bardxhong.centralweatherbureau.data

import com.bardxhong.centralweatherbureau.retrofit.IntervalData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ForecastItem(override val data: IntervalData) : IItem<IntervalData>

@Parcelize
data class ImageItem(override val data: IntervalData) : IItem<IntervalData>