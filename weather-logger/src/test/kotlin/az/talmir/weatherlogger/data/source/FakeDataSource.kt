package az.talmir.weatherlogger.data.source

import az.talmir.weatherlogger.data.IForecastDataSource
import az.talmir.weatherlogger.data.Result
import az.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import az.talmir.weatherlogger.helpers.cityNameById
import az.talmir.weatherlogger.helpers.toForecast
import az.talmir.weatherlogger.helpers.weather.Forecast

class FakeDataSource(private val forecasts: MutableList<Forecast>? = mutableListOf()) :
    IForecastDataSource {
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
