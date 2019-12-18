package com.talmir.weatherlogger

import android.app.Application
import com.talmir.weatherlogger.data.IForecastsRepository

class WeatherLoggerApp : Application() {
    // TODO: Consider a Dependency Injection framework.
    val forecastsRepository: IForecastsRepository
        get() = ServiceLocator.provideForecastRepository(this)
}
