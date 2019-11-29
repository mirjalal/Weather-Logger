package com.talmir.weatherlogger.helpers.weather

// TODO: rewrite this section
object WeatherTypes {
    // https://openweathermap.org/weather-conditions
    const val THUNDERSTORM = "1"
    const val DRIZZLE = "2"
    const val RAIN = "3"
    const val SNOW = "4"
    const val ATMOSPHERE = "5"
    const val CLEAR = "6"
    const val CLOUDS = "7"

    internal fun getWeatherType(weatherCode: Int) =
        when (weatherCode) {
            in 200..299 -> THUNDERSTORM
            in 300..499 -> DRIZZLE
            in 500..599 -> RAIN
            in 600..699 -> SNOW
            in 701..799 -> ATMOSPHERE
            800 -> CLEAR
            else -> CLOUDS
        }
}
