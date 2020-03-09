package com.talmir.weatherlogger.data.source

import com.talmir.weatherlogger.data.IForecastDataSource
import com.talmir.weatherlogger.data.Result
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import com.talmir.weatherlogger.helpers.cityNameById
import com.talmir.weatherlogger.helpers.toForecast
import com.talmir.weatherlogger.helpers.weather.Forecast

class FakeDataSource(private val forecasts: MutableList<Forecast>? = mutableListOf()) : IForecastDataSource {
    override suspend fun getForecastData(): Result<List<Forecast>> {
        forecasts?.let { return Result.Success(it) }
        return Result.Error(Exception("Forecasts not found"))
    }

    override suspend fun saveForecastData(forecastData: List<ForecastDataEntity>) {
        forecasts?.addAll(forecastData.toForecast())
    }

    override suspend fun getSingleCityForecastData(cityId: Long): Result<List<Forecast>> {
        forecasts?.let { list -> return Result.Success(list.filter { it.cityId == cityId }) }
        return Result.Error(Exception("No forecast data found for ${cityId.cityNameById()}"))
    }
}
