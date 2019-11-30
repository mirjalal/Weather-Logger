package com.talmir.weatherlogger.helpers

import com.talmir.weatherlogger.data.source.local.room.city_forecast_data.CityForecastData
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import com.talmir.weatherlogger.data.source.remote.network.ApiModel
import com.talmir.weatherlogger.helpers.weather.Forecast
import kotlin.math.round
import kotlin.math.roundToInt

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
                it.sunset,
                it.requestTime
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
                it.main.temp.roundToInt(),
                it.main.pressure,
                it.main.humidity,
                round(it.wind.speed * 100) / 100,
                it.sunEvents.sunrise,
                it.sunEvents.sunset,
                it.requestTime
            )
        }
    }
    else -> listOf()
}

fun CityForecastData.toForecast() =
    cityForecastData.toForecast()
