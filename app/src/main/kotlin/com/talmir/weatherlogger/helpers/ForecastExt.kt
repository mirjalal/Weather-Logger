package com.talmir.weatherlogger.helpers

import com.talmir.weatherlogger.R
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import com.talmir.weatherlogger.data.source.remote.network.ApiModel
import com.talmir.weatherlogger.helpers.weather.Forecast

@Suppress("unchecked_cast")
inline fun <reified E> List<E>.toForecast() =
    when (E::class) {
        ForecastDataEntity::class -> {
            this as List<ForecastDataEntity>
            listOf(
                Forecast(
                    "Baku", R.drawable.baku_maiden_tower, this[0].weatherTemperature.toString(), this[0].sunrise, this[0].sunset, this[0].weatherName),
                Forecast(
                    "Sumgait", R.drawable.sumgait_industrial_city, this[1].weatherTemperature.toString(), this[1].sunrise, this[1].sunset, this[1].weatherName),
                Forecast(
                    "Lankaran", R.drawable.lenkaran_samovar, this[2].weatherTemperature.toString(), this[2].sunrise, this[2].sunset, this[2].weatherName),
                Forecast(
                    "Shamakhi", R.drawable.shamakhi_observatory_city, this[3].weatherTemperature.toString(), this[3].sunrise, this[3].sunset, this[3].weatherName),
                Forecast(
                    "Goychay", R.drawable.baku_maiden_tower, this[4].weatherTemperature.toString(), this[4].sunrise, this[4].sunset, this[4].weatherName)
            )
        }
        ApiModel.WeatherList::class -> {
            this as List<ApiModel.WeatherList>
            listOf(
                Forecast(
                    "Baku", R.drawable.baku_maiden_tower, this[0].main.temp.toString(), this[0].sys.sunrise, this[0].sys.sunset, this[0].weather[0].main),
                Forecast(
                    "Sumgait", R.drawable.sumgait_industrial_city, this[1].main.temp.toString(), this[1].sys.sunrise, this[1].sys.sunset, this[1].weather[0].main),
                Forecast(
                    "Lankaran", R.drawable.lenkaran_samovar, this[2].main.temp.toString(), this[2].sys.sunrise, this[2].sys.sunset, this[2].weather[0].main),
                Forecast(
                    "Shamakhi", R.drawable.shamakhi_observatory_city, this[3].main.temp.toString(), this[3].sys.sunrise, this[3].sys.sunset, this[3].weather[0].main),
                Forecast(
                    "Goychay", R.drawable.baku_maiden_tower, this[4].main.temp.toString(), this[4].sys.sunrise, this[4].sys.sunset, this[4].weather[0].main)
            )
        }
        else -> listOf()
    }
