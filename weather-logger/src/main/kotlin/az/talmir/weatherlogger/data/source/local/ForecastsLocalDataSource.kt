package az.talmir.weatherlogger.data.source.local

import az.talmir.weatherlogger.data.IForecastDataSource
import az.talmir.weatherlogger.data.Result
import az.talmir.weatherlogger.data.source.local.room.city_forecast_data.CityForecastDataDao
import az.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataDao
import az.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import az.talmir.weatherlogger.helpers.toForecast
import az.talmir.weatherlogger.helpers.weather.Forecast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ForecastsLocalDataSource internal constructor(
    private val forecastDataDao: ForecastDataDao,
    private val cityForecastDataDao: CityForecastDataDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : IForecastDataSource {

    override suspend fun getForecastData(): Result<List<Forecast>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Result.Success(forecastDataDao.getCitiesLastForecastData().toForecast().sortedBy { it.cityId })
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun getSingleCityForecastData(cityId: Long): Result<List<Forecast>> =
        withContext(ioDispatcher) {
            return@withContext try {
                Result.Success(cityForecastDataDao.getSingleCityForecastData(cityId).toForecast())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun saveForecastData(forecastData: List<ForecastDataEntity>) =
        withContext(ioDispatcher) {
            forecastDataDao.saveData(forecastData)
        }
}
