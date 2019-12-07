package com.talmir.weatherlogger.helpers

import com.talmir.weatherlogger.data.source.local.room.city_forecast_data.CityForecastData
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import com.talmir.weatherlogger.data.source.remote.network.ApiModel
import com.talmir.weatherlogger.helpers.weather.Forecast
import kotlin.math.round
import kotlin.math.roundToInt

@JvmName("castForecastEntityToForecast")
fun List<ForecastDataEntity>.toForecast() =
    map {
        Forecast(
            it.cityId,
            it.cityId.cityNameById(),
            it.cityId.cityIconById(),
            it.weatherName,
            it.weatherTemperature,
            it.weatherPressure,
            it.weatherHumidity,
            round(it.weatherWindSpeed * 100) / 100,
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

fun CityForecastData.toForecast() =
    cityForecastData.toForecast()
