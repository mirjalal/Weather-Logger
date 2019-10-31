package com.talmir.weatherlogger.data

import com.talmir.weatherlogger.helpers.weather.Forecast

interface ForecastsDataSource {
    suspend fun getAllForecasts(): Result<List<Forecast>>
}
