package az.talmir.weatherlogger.data

import az.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import az.talmir.weatherlogger.helpers.cityIconById
import az.talmir.weatherlogger.helpers.cityNameById
import az.talmir.weatherlogger.helpers.weather.Forecast

class FakeForecastRepository : IForecastRepository {

    private var forecastsServiceData: LinkedHashMap<Long, Forecast> = LinkedHashMap()

    override suspend fun getSingleCityForecastData(cityId: Long): Result<List<Forecast>> =
        Result.Success(forecastsServiceData.values.filter { it.cityId == cityId })

    override suspend fun getForecastData(): Result<List<Forecast>> {
        return Result.Success(forecastsServiceData.values.toList())
    }

    override suspend fun saveForecastData(forecastData: List<ForecastDataEntity>) {
        forecastData.forEach {
            forecastsServiceData[it.cityId] = Forecast(it.cityId, it.cityId.cityNameById(), it.cityId.cityIconById(), it.weatherCode, it.weatherTemperature, it.weatherPressure, it.weatherHumidity, it.weatherWindSpeed, it.sunrise, it.sunset, it.requestTime)
        }
    }

    fun addForecasts(vararg forecasts: Forecast) {
        for (forecast in forecasts)
            forecastsServiceData[forecast.cityId] = forecast
    }
}
