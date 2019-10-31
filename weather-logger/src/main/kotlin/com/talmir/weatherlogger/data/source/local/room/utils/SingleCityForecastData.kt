package com.talmir.weatherlogger.data.source.local.room.utils

data class SingleCityForecastData(
    val forecastDataId: Int,
    val cityName: String,
    val type: String,
    val temperature: Int
) {
    constructor() : this(0, "", "", 0)
}
