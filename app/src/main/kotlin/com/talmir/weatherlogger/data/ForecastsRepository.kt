package com.talmir.weatherlogger.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

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
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

}
