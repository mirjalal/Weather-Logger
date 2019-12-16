package com.talmir.weatherlogger.data.source.local

import com.talmir.weatherlogger.data.ForecastsDataSource
import com.talmir.weatherlogger.data.Result
import com.talmir.weatherlogger.data.source.local.room.city_forecast_data.CityForecastDataDao
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataDao
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import com.talmir.weatherlogger.helpers.toForecast
import com.talmir.weatherlogger.helpers.weather.Forecast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.Exception

class ForecastsLocalDataSource internal constructor(
    private val forecastDataDao: ForecastDataDao,
    private val cityForecastDataDao: CityForecastDataDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ForecastsDataSource {

    override suspend fun getForecastData(): Result<List<Forecast>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Result.Success(forecastDataDao.getCitiesLastForecastData().toForecast().sortedBy { it.cityId })
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    suspend fun getSingleCityForecastData(cityId: Long): Result<List<Forecast>> =
        withContext(ioDispatcher) {
            return@withContext try {
                println(cityId)
                Result.Success(cityForecastDataDao.getCityForecastData(cityId).toForecast())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    suspend fun saveForecastData(forecastData: List<ForecastDataEntity>) =
        withContext(ioDispatcher) {
            forecastDataDao.saveData(forecastData)
        }
}
