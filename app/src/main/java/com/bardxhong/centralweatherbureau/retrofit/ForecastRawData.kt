package com.bardxhong.centralweatherbureau.retrofit

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Forecast(
    @SerializedName("records")
    val records: Records,
    @SerializedName("result")
    val result: Result,
    @SerializedName("success")
    val success: String
): Parcelable

@Parcelize
data class Records(
    @SerializedName("datasetDescription")
    val datasetDescription: String,
    @SerializedName("location")
    val location: List<Location>
): Parcelable

@Parcelize
data class Result(
    @SerializedName("fields")
    val fields: List<Field>,
    @SerializedName("resource_id")
    val resourceId: String
): Parcelable

@Parcelize
data class Location(
    @SerializedName("locationName")
    val locationName: String,
    @SerializedName("weatherElement")
    val weatherElementList: List<WeatherElement>
): Parcelable

@Parcelize
data class WeatherElement(
    @SerializedName("elementName")
    val elementName: String,
    @SerializedName("time")
    val intervalDataList: List<IntervalData>
): Parcelable

@Parcelize
data class IntervalData(
    @SerializedName("endTime")
    val endTime: String,
    @SerializedName("parameter")
    val detail: Detail,
    @SerializedName("startTime")
    val startTime: String
): Parcelable

@Parcelize
data class Detail(
    @SerializedName("parameterName")
    val parameterName: String,
    @SerializedName("parameterUnit")
    val parameterUnit: String
): Parcelable

@Parcelize
data class Field(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String
): Parcelable