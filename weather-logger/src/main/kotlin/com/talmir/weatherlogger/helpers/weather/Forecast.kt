package com.talmir.weatherlogger.helpers.weather

data class Forecast(
    internal val cityId: Long,
    internal val cityName: String,
    internal val cityIcon: Int,
    internal val weatherType: String,
    internal val temperature: Int,
    internal val pressure: Int,
    internal val humidity: Int,
    internal val windSpeed: Float,
    internal val sunrise: Int,
    internal val sunset: Int)
