package com.talmir.weatherlogger.data

import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import com.talmir.weatherlogger.helpers.weather.Forecast

interface IForecastsRepository {
    suspend fun getCityForecastData(cityId: Long): Result<List<Forecast>>
    suspend fun getForecastData(): Result<List<Forecast>>
    suspend fun saveForecastData(forecastData: List<ForecastDataEntity>)
}