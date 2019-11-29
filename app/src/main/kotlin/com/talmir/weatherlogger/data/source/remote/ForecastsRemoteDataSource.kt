package com.talmir.weatherlogger.data.source.remote

import com.talmir.weatherlogger.data.ForecastsDataSource
import com.talmir.weatherlogger.data.Result
import com.talmir.weatherlogger.data.source.remote.network.ForecastRemoteDataRetrieverApi.forecastDataRetrieverService
import com.talmir.weatherlogger.helpers.toForecast
import com.talmir.weatherlogger.helpers.weather.Forecast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ForecastsRemoteDataSource internal constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): ForecastsDataSource {

    override suspend fun getForecastData(): Result<List<Forecast>> = withContext(ioDispatcher) {
        return@withContext try {
            val networkRequest = forecastDataRetrieverService.getForecastData()
            val data = networkRequest.body()?.list

            if (networkRequest.isSuccessful && data != null)
                Result.Success(data.toForecast())
            else
                Result.Error(Error("Network request failed!"))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
