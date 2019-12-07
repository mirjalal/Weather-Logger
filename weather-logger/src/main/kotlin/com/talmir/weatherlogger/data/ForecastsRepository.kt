package com.talmir.weatherlogger.data

import com.talmir.weatherlogger.data.source.local.ForecastsLocalDataSource
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import com.talmir.weatherlogger.data.source.remote.ForecastsRemoteDataSource
import com.talmir.weatherlogger.helpers.weather.Forecast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Concrete implementation to load forecasts from the data sources into a cache.
 *
 * To simplify the sample, this repository only uses the local data source only if
 * the remote data source fails. Remote is the source of truth.
 */
class ForecastsRepository(
    private val forecastsRemoteDataSource: ForecastsRemoteDataSource,
    private val forecastsLocalDataSource: ForecastsLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) {

    suspend fun getCityForecastData(cityId: Long) =
        withContext(ioDispatcher) {
            return@withContext forecastsLocalDataSource.getSingleCityForecastData(cityId)
        }

    suspend fun getForecastData(): Result<List<Forecast>> =
        withContext(ioDispatcher) {
            return@withContext fetchDataFromRemoteOrLocal()
        }

    private suspend fun fetchDataFromRemoteOrLocal(): Result<List<Forecast>> {
        // remote data first
        when (val remoteForecastData = forecastsRemoteDataSource.getForecastData()) {
            is Result.Error -> {
                val localForecastData = forecastsLocalDataSource.getForecastData()
                if (localForecastData is Result.Success)
                    return localForecastData
            }
            is Result.Success -> {
                refreshLocalDataSource(remoteForecastData.data)
                return remoteForecastData
            }
            else -> throw IllegalStateException()
        }

        return Result.Error(Exception("Error fetching data from remote and local"))
    }

    private suspend fun refreshLocalDataSource(data: List<Forecast>) {
        val forecastEntityData = data.map {
            ForecastDataEntity(
                it.cityId,
                it.weatherType,
                it.temperature,
                it.pressure,
                it.humidity,
                it.windSpeed,
                it.sunrise,
                it.sunset,
                it.requestTime
            )
        }
        withContext(ioDispatcher) {
            forecastsLocalDataSource.saveForecastData(forecastEntityData)
        }
    }
}
