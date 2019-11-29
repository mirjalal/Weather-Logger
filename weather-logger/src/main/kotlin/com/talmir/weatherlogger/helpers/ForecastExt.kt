package com.talmir.weatherlogger.helpers

import com.talmir.weatherlogger.data.source.local.room.city_forecast_data.CityForecastData
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import com.talmir.weatherlogger.data.source.remote.network.ApiModel
import com.talmir.weatherlogger.helpers.weather.Forecast

@Suppress("unchecked_cast")
inline fun <reified E> List<E>.toForecast() = when (E::class) {
    ForecastDataEntity::class -> {
        (this as List<ForecastDataEntity>).map {
            Forecast(
                it.cityId,
                it.cityId.cityNameById(),
                it.cityId.cityIconById(),
                it.weatherName,
                it.weatherTemperature,
                it.weatherPressure,
                it.weatherHumidity,
                it.weatherWindSpeed,
                it.sunrise,
                it.sunset
            )
        }
    }
    ApiModel.WeatherList::class -> {
        (this as List<ApiModel.WeatherList>).map {
            Forecast(
                it.cityId.toLong(),
                it.cityId.toLong().cityNameById(),
                it.cityId.toLong().cityIconById(),
                it.weather[0].main,
                it.main.temp.toInt(),
                it.main.pressure,
                it.main.humidity,
                it.wind.speed.toFloat(),
                it.sys.sunrise,
                it.sys.sunset
            )
        }
    }
    else -> listOf()
}

fun CityForecastData.toForecast() =
    cityForecastData.toForecast()
