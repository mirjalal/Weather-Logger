package com.talmir.weatherlogger.data

import com.talmir.weatherlogger.R
import com.talmir.weatherlogger.data.source.FakeDataSource
import com.talmir.weatherlogger.helpers.Constants
import com.talmir.weatherlogger.helpers.weather.Forecast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import java.util.Date

class ForecastsRepositoryTest {

    private val forecastData1 = Forecast(Constants.BAKU_CITY_ID, "Baku", R.drawable.baku_maiden_tower, 761, 9, 758, 65, 5.2f, 123456789, 987654321, Date())
    private val forecastData2 = Forecast(Constants.SUMGAIT_CITY_ID, "Sumgait", R.drawable.sumgait_industrial_city, 761, 9, 758, 65, 5.2f, 123456789, 987654321, Date())
    private val forecastData3 = Forecast(Constants.LENKARAN_CITY_ID, "Lenkaran", R.drawable.lenkaran_samovar, 761, 9, 758, 65, 5.2f, 123456789, 987654321, Date())
    private val forecastData4 = Forecast(Constants.SHAMAKHI_CITY_ID, "Shamakhi", R.drawable.shamakhi_observatory_city, 761, 9, 758, 65, 5.2f, 123456789, 987654321, Date())
    private val forecastData5 = Forecast(Constants.GOYCHAY_CITY_ID, "Goychay", R.drawable.baku_maiden_tower, 761, 9, 758, 65, 5.2f, 123456789, 987654321, Date())

    private val remoteForecasts = listOf(forecastData1, forecastData2, forecastData3).sortedBy { it.cityId }
    private val localForecasts = listOf(forecastData4, forecastData5).sortedBy { it.cityId }

    private lateinit var forecastsRemoteDataSource: FakeDataSource
    private lateinit var forecastsLocalDataSource: FakeDataSource

    // class under test
    private lateinit var forecastsRepository: ForecastsRepository

    @Before
    fun createRepository() {
        forecastsRemoteDataSource = FakeDataSource(remoteForecasts.toMutableList())
        forecastsLocalDataSource = FakeDataSource(localForecasts.toMutableList())

        forecastsRepository = ForecastsRepository(forecastsRemoteDataSource, forecastsLocalDataSource, Dispatchers.Unconfined)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getForecastData_requestForecastsFromRemoteDataSource() = runBlockingTest {
        // when forecasts are requested from the forecasts repository
        val forecasts = forecastsRepository.getForecastData() as Result.Success
        // then forecasts are loaded from the remote data souce
        assertThat(forecasts.data, IsEqual(remoteForecasts))
    }
}
