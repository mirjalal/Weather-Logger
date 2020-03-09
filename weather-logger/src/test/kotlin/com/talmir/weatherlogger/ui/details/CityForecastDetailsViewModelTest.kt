package com.talmir.weatherlogger.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.talmir.weatherlogger.CoroutineTestRule
import com.talmir.weatherlogger.R
import com.talmir.weatherlogger.data.FakeForecastRepository
import com.talmir.weatherlogger.getOrAwaitValue
import com.talmir.weatherlogger.helpers.Constants
import com.talmir.weatherlogger.helpers.weather.Forecast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.not
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.Date

class CityForecastDetailsViewModelTest {
    private lateinit var forecastsRepository: FakeForecastRepository

    // subject under test
    private lateinit var forecastsViewModel: CityForecastDetailsViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setupViewModel() {
        forecastsRepository = FakeForecastRepository()
        val forecastData1 = Forecast(Constants.BAKU_CITY_ID, "Baku", R.drawable.baku_maiden_tower, 761, 9, 758, 65, 5.2f, 123456789, 987654321, Date())
        val forecastData2 = Forecast(Constants.SUMGAIT_CITY_ID, "Sumgait", R.drawable.sumgait_industrial_city, 761, 9, 758, 65, 5.2f, 123456789, 987654321, Date())
        val forecastData3 = Forecast(Constants.LENKARAN_CITY_ID, "Lenkaran", R.drawable.lenkaran_samovar, 761, 9, 758, 65, 5.2f, 123456789, 987654321, Date())
        val forecastData4 = Forecast(Constants.SHAMAKHI_CITY_ID, "Shamakhi", R.drawable.shamakhi_observatory_city, 761, 9, 758, 65, 5.2f, 123456789, 987654321, Date())
        val forecastData5 = Forecast(Constants.GOYCHAY_CITY_ID, "Goychay", R.drawable.goychay_pomegranate_city, 761, 9, 758, 65, 5.2f, 123456789, 987654321, Date())

        forecastsRepository.addForecasts(forecastData1, forecastData2, forecastData3, forecastData4, forecastData5)

        forecastsViewModel = CityForecastDetailsViewModel(forecastsRepository, forecastData3.cityId)
    }

    @Test
    fun getForecastDetailsData_returnsNotNullValue() {
        val value = forecastsViewModel.cityForecastDetails.getOrAwaitValue() //value
        assertThat(value, not(nullValue()))
    }
}
