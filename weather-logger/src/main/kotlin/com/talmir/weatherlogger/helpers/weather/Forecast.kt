package com.talmir.weatherlogger.helpers.weather

import java.util.Date

data class Forecast(
    val cityId: Long,
    val cityName: String,
    val cityIcon: Int,
    val weatherType: String,
    val temperature: Int,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Float,
    val sunrise: Int,
    val sunset: Int,
    val requestTime: Date)
