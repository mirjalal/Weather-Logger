package com.talmir.weatherlogger.data.source.local

import com.talmir.weatherlogger.data.ForecastsDataSource
import com.talmir.weatherlogger.data.Result
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataDao
import com.talmir.weatherlogger.helpers.toForecast
import com.talmir.weatherlogger.helpers.weather.Forecast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ForecastsLocalDataSource internal constructor(
    private val forecastDataDao: ForecastDataDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ForecastsDataSource {

    override suspend fun getAllForecasts(): Result<List<Forecast>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(forecastDataDao.getWeatherData().toForecast())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
