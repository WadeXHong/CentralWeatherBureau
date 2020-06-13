package com.bardxhong.centralweatherbureau.retrofit

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("records")
    val records: Records,
    @SerializedName("result")
    val result: Result,
    @SerializedName("success")
    val success: String
)

data class Records(
    @SerializedName("datasetDescription")
    val datasetDescription: String,
    @SerializedName("location")
    val location: List<Location>
)

data class Result(
    @SerializedName("fields")
    val fields: List<Field>,
    @SerializedName("resource_id")
    val resourceId: String
)

data class Location(
    @SerializedName("locationName")
    val locationName: String,
    @SerializedName("weatherElement")
    val weatherElementList: List<WeatherElement>
)

data class WeatherElement(
    @SerializedName("elementName")
    val elementName: String,
    @SerializedName("time")
    val intervalDataList: List<IntervalData>
)

data class IntervalData(
    @SerializedName("endTime")
    val endTime: String,
    @SerializedName("parameter")
    val detail: Detail,
    @SerializedName("startTime")
    val startTime: String
)

data class Detail(
    @SerializedName("parameterName")
    val parameterName: String,
    @SerializedName("parameterUnit")
    val parameterUnit: String
)

data class Field(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String
)