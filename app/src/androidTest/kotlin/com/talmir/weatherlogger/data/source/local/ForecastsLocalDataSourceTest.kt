package com.talmir.weatherlogger.data.source.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.talmir.weatherlogger.data.source.local.room.cities.CityDao
import com.talmir.weatherlogger.data.source.local.room.cities.CityEntity
import com.talmir.weatherlogger.data.source.local.room.city_forecast_data.CityForecastDataDao
import com.talmir.weatherlogger.data.source.local.room.city_forecast_data.CityForecastData
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataDao
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import com.talmir.weatherlogger.data.source.local.room.utils.AppDatabase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ForecastsLocalDataSourceTest {

    private lateinit var db: AppDatabase
    private lateinit var forecastDataDao: ForecastDataDao
    private lateinit var cityDao: CityDao
    private lateinit var cityForecastDataDao: CityForecastDataDao

    private val forecasts = listOf(
        ForecastDataEntity(1L, "Clouds", 14, 762, 67, 28f, 32131132, 1231124, id = 1L), // data for Baku
        ForecastDataEntity(1L, "Rain", 16, 762, 82, 36f, 32131132, 1231124, id = 2L),   // data for Baku
        ForecastDataEntity(1L, "Sunny", 35, 762, 44, 14f, 32131132, 1231124, id = 3L),  // data for Baku
        ForecastDataEntity(3L, "Windy", 15, 762, 67, 65f, 32131132, 1231124, id = 4L),  // data for Lenkaran
        ForecastDataEntity(3L, "Storm", 9, 762, 67, 87f, 32131132, 1231124, id = 5L)    // data for Lenkaran
    )
    private val cities = listOf(
        CityEntity(1L, "BAKU"),
        CityEntity(2L, "SUMGAIT"),
        CityEntity(3L, "LENKARAN"),
        CityEntity(4L, "SHAMAKHI"),
        CityEntity(5L, "GOYCHAY")
    )
    private val bakuCityForecastData = CityForecastData(cities[0], forecasts.subList(0, 3))
    private val lenkaranCityForecastData = CityForecastData(cities[2], forecasts.subList(3, 5))

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val ctx = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(ctx, AppDatabase::class.java).build()

        forecastDataDao = db.forecastDataDao()
        cityDao = db.cityDao()
        cityForecastDataDao = db.cityForecastDataDao()
    }

    @Test
    fun saveForecastData_success() {
        runBlocking {
            cityDao.saveData(cities)
            forecastDataDao.saveData(forecasts)

            assertThat(cityForecastDataDao.getCityForecastData(1L), `is`(equalTo(bakuCityForecastData)))
            assertThat(cityForecastDataDao.getCityForecastData(3L), `is`(equalTo(lenkaranCityForecastData)))

            assertThat(forecastDataDao.getForecastData(), `is`(equalTo(forecasts)))
        }
    }

    @After
    fun closeDb() = db.close()
}
