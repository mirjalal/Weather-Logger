package az.talmir.weatherlogger.helpers

import az.talmir.weatherlogger.data.source.local.room.city_forecast_data.CityForecastData
import az.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import az.talmir.weatherlogger.data.source.remote.network.ApiModel
import az.talmir.weatherlogger.helpers.weather.Forecast
import kotlin.math.roundToInt

@JvmName("castForecastEntityToForecast")
fun List<ForecastDataEntity>.toForecast() =
    map {
        Forecast(
            it.cityId,
            it.cityId.cityNameById(),
            it.cityId.cityIconById(),
            it.weatherCode,
            it.weatherTemperature,
            (it.weatherPressure * 0.750_061_683).toInt(),
            it.weatherHumidity,
            it.weatherWindSpeed,
            it.sunrise,
            it.sunset,
            it.requestTime
        )
    }

@JvmName("castApiModelToForecast")
fun List<ApiModel.WeatherList>.toForecast() =
    map {
        Forecast(
            it.cityId.toLong().cityIdById(),
            it.cityId.toLong().cityNameById(),
            it.cityId.toLong().cityIconById(),
            it.weather[0].id,
            it.main.temp.roundToInt(),
            (it.main.pressure * 0.750_061_683).toInt(),
            it.main.humidity,
            it.wind.speed,
            it.sunEvents.sunrise,
            it.sunEvents.sunset,
            it.requestTime
        )
    }

fun CityForecastData.toForecast() =
    cityForecastData.toForecast()
