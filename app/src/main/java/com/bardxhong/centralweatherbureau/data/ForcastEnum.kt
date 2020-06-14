package com.bardxhong.centralweatherbureau.data

enum class CityEnum(val cityName: String) {
    TAIPEI_CITY("臺北市"),
    NEW_TAIPEI_CITY("新北市"),
}

enum class ElementEnum(val elementName: String) {
    MINIMUM_TEMPERATURE("MinT"),
    MAXIMUM_TEMPERATURE("MaxT"),
    WX("Wx"),
    POP("PoP"),
    CI("CI"),
}