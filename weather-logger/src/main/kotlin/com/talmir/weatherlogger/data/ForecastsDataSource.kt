package com.talmir.weatherlogger.data

import com.talmir.weatherlogger.helpers.weather.Forecast

interface ForecastsDataSource {
    suspend fun getForecastData(): Result<List<Forecast>>
}
