package com.talmir.weatherlogger.data

import com.talmir.weatherlogger.helpers.weather.Forecast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Concrete implementation to load forecasts
 * from the data sources into a cache.
 *
 * To simplify the sample, this repository
 * only uses the local data source only if
 * the remote data source fails. Remote is
 * the source of truth.
 */
class ForecastsRepository(
    private val tasksRemoteDataSource: ForecastsDataSource,
    private val tasksLocalDataSource: ForecastsDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun getForecastData(): Result<List<Forecast>> {
        return withContext(ioDispatcher) {
            val localForecastData = tasksLocalDataSource.getAllForecasts()

            if (localForecastData.succeeded)
                localForecastData
            else {
                val remoteForecastData = tasksRemoteDataSource.getAllForecasts()

                if (remoteForecastData.succeeded)
                    remoteForecastData
                else
                    listOf()
            }
        }
    }
}
