package com.talmir.weatherlogger.helpers

import com.talmir.weatherlogger.helpers.weather.WeatherTypes

fun Long.cityIdById() =
    when (this) {
        587084L -> 1L
        584923L -> 2L
        147613L -> 3L
        585156L -> 4L
        else -> 5L
    }

fun Long.cityNameById() =
    when (this) {
        1L, 587084L -> "Baku"
        2L, 584923L -> "Sumgait"
        3L, 147613L -> "Lenkaran"
        4L, 585156L -> "Shamakhi"
        else -> "Goychay"
    }

fun Long.cityIconById() =
    when (this) {
        1L, 587084L -> com.talmir.weatherlogger.R.drawable.baku_maiden_tower
        2L, 584923L -> com.talmir.weatherlogger.R.drawable.sumgait_industrial_city
        3L, 147613L -> com.talmir.weatherlogger.R.drawable.lenkaran_samovar
        4L, 585156L -> com.talmir.weatherlogger.R.drawable.shamakhi_observatory_city
        else -> com.talmir.weatherlogger.R.drawable.baku_maiden_tower
    }

fun String.weatherToIcon() =
    when (this) {
        WeatherTypes.THUNDERSTORM -> com.talmir.weatherlogger.R.drawable.weather_thunderstorm
        WeatherTypes.DRIZZLE -> com.talmir.weatherlogger.R.drawable.weather_drizzle
        WeatherTypes.RAIN -> com.talmir.weatherlogger.R.drawable.weather_rain
        WeatherTypes.SNOW -> com.talmir.weatherlogger.R.drawable.weather_snow
        WeatherTypes.ATMOSPHERE -> com.talmir.weatherlogger.R.drawable.weather_atmosphere
        WeatherTypes.CLEAR -> com.talmir.weatherlogger.R.drawable.weather_clear
        WeatherTypes.CLOUDS -> com.talmir.weatherlogger.R.drawable.weather_clouds
        WeatherTypes.MIST -> com.talmir.weatherlogger.R.drawable.weather_mist
        else -> throw IllegalArgumentException()
    }
