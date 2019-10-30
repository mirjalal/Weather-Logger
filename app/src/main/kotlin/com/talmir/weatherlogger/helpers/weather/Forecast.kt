package com.talmir.weatherlogger.helpers.weather

data class Forecast(
    internal val cityName: String,
    internal val cityIcon: Int,
    internal val temperature: String,
    internal val sunrise: Int,
    internal val sunset: Int,
    internal val weatherType: String
)
