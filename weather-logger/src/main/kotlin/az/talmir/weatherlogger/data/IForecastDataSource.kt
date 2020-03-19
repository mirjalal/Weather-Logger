package az.talmir.weatherlogger.data

import az.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import az.talmir.weatherlogger.helpers.weather.Forecast

interface IForecastDataSource {
    suspend fun getForecastData(): Result<List<Forecast>>
    suspend fun saveForecastData(forecastData: List<ForecastDataEntity>)
    suspend fun getSingleCityForecastData(cityId: Long): Result<List<Forecast>>
}
