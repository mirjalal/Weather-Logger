package com.talmir.weatherlogger.helpers.weather

object WeatherStation {
    lateinit var forecasts: List<Forecast>
        private set

    fun createForecast(forecasts: List<Forecast>) {
        this.forecasts = forecasts
    }
}
