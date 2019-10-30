package com.talmir.weatherlogger

import android.app.Application
import com.talmir.weatherlogger.data.ForecastsRepository
import com.talmir.weatherlogger.data.source.local.ForecastsLocalDataSource
import com.talmir.weatherlogger.data.source.local.room.utils.AppDatabase
import com.talmir.weatherlogger.data.source.remote.ForecastsRemoteDataSource

class WeatherLoggerApp : Application() {
    // TODO: Consider a Dependency Injection framework.
    val forecastsRepository: ForecastsRepository by lazy {
        ForecastsRepository(
            ForecastsRemoteDataSource(),
            ForecastsLocalDataSource(AppDatabase.getDatabase(this).forecastDataDao())
        )
    }
}
