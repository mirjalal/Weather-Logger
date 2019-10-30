package com.talmir.weatherlogger.data.source.remote.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiModel(
    @Json(name = "list")
    val list: List<WeatherList>
) {
    @JsonClass(generateAdapter = true)
    data class WeatherList(
        @Json(name = "clouds")
        val clouds: Clouds,
        @Json(name = "main")
        val main: Main,
        @Json(name = "name")
        val name: String,
        @Json(name = "sys")
        val sys: Sys,
        @Json(name = "weather")
        val weather: List<Weather>,
        @Json(name = "wind")
        val wind: Wind
    ) {
        @JsonClass(generateAdapter = true)
        data class Clouds(
            @Json(name = "all")
            val all: Int
        )

        @JsonClass(generateAdapter = true)
        data class Main(
            @Json(name = "humidity")
            val humidity: Int,
            @Json(name = "pressure")
            val pressure: Int,
            @Json(name = "temp")
            val temp: Double,
            @Json(name = "temp_max")
            val tempMax: Int,
            @Json(name = "temp_min")
            val tempMin: Int
        )

        @JsonClass(generateAdapter = true)
        data class Sys(
            @Json(name = "country")
            val country: String,
            @Json(name = "sunrise")
            val sunrise: Int,
            @Json(name = "sunset")
            val sunset: Int,
            @Json(name = "timezone")
            val timezone: Int
        )

        @JsonClass(generateAdapter = true)
        data class Weather(
            @Json(name = "description")
            val description: String,
            @Json(name = "icon")
            val icon: String,
            @Json(name = "id")
            val id: Int,
            @Json(name = "main")
            val main: String
        )

        @JsonClass(generateAdapter = true)
        data class Wind(
            @Json(name = "deg")
            val deg: Int,
            @Json(name = "speed")
            val speed: Double
        )
    }
}