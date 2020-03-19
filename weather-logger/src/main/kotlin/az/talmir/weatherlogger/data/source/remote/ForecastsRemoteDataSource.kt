package az.talmir.weatherlogger.data.source.remote

import az.talmir.weatherlogger.data.IForecastDataSource
import az.talmir.weatherlogger.data.Result
import az.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import az.talmir.weatherlogger.data.source.remote.network.ForecastRemoteDataRetrieverApi.forecastDataRetrieverService
import az.talmir.weatherlogger.helpers.toForecast
import az.talmir.weatherlogger.helpers.weather.Forecast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ForecastsRemoteDataSource internal constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): IForecastDataSource {

    override suspend fun getForecastData(): Result<List<Forecast>> =
        withContext(ioDispatcher) {
            return@withContext try {
                val networkRequest = forecastDataRetrieverService.getForecastData()
                val data = networkRequest.body()?.currentWeather

                if (networkRequest.isSuccessful && data != null)
                    Result.Success(data.toForecast())
                else
                    Result.Error(Error("Network request failed!"))
            } catch (e: Exception) {
                Result.Error(e)
            }
        }

    override suspend fun saveForecastData(forecastData: List<ForecastDataEntity>) =
        throw NotImplementedError("This method should not to be implemented for remote data source!")

    override suspend fun getSingleCityForecastData(cityId: Long) =
        throw NotImplementedError("This method should not to be implemented for remote data source!")
}
