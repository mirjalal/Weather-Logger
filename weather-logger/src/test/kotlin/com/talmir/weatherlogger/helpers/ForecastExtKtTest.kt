package com.talmir.weatherlogger.helpers

import com.talmir.weatherlogger.R
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import com.talmir.weatherlogger.data.source.remote.network.ApiModel
import com.talmir.weatherlogger.helpers.weather.Forecast
import org.junit.Test
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import java.util.Date

class ForecastExtKtTest {

    @Test
    fun toForecast_forecastEntityList_returnsForecastList() {
        val requestTime = Date()

        val forecastEntityList = listOf(
            ForecastDataEntity(1L, "Clouds", 14, 762, 67, 28f, 32131132, 1231124, requestTime),
            ForecastDataEntity(2L, "Rain", 16, 762, 82, 36f, 32131132, 1231124, requestTime),
            ForecastDataEntity(3L, "Sunny", 35, 762, 44, 14f, 32131132, 1231124, requestTime),
            ForecastDataEntity(4L, "Windy", 15, 762, 67, 65f, 32131132, 1231124, requestTime),
            ForecastDataEntity(5L, "Storm", 9, 762, 67, 87f, 32131132, 1231124, requestTime)
        )

        val forecastList = listOf(
            Forecast(1L,"Baku", R.drawable.baku_maiden_tower, "Clouds", 14, 762, 67, 28f, 32131132, 1231124, requestTime),
            Forecast(2L,"Sumgait", R.drawable.sumgait_industrial_city, "Rain", 16, 762, 82, 36f, 32131132, 1231124, requestTime),
            Forecast(3L,"Lenkaran", R.drawable.lenkaran_samovar, "Sunny", 35, 762, 44, 14f, 32131132, 1231124, requestTime),
            Forecast(4L,"Shamakhi", R.drawable.shamakhi_observatory_city, "Windy", 15, 762, 67, 65f, 32131132, 1231124, requestTime),
            Forecast(5L,"Goychay", R.drawable.baku_maiden_tower, "Storm", 9, 762, 67, 87f, 32131132, 1231124, requestTime)
        )

        assertThat(forecastEntityList.toForecast(), `is`(equalTo(forecastList)))
    }

    @Test
    fun toForecast_weatherList_returnsForecastList() {
        val requestTime = Date()

        val apiModelWeatherList = listOf(
            ApiModel.WeatherList(
                ApiModel.WeatherList.Main(67, 762, 14.0f),
                listOf(ApiModel.WeatherList.Weather("", "", 1, "Clouds")),
                ApiModel.WeatherList.Wind(28.0f),
                ApiModel.WeatherList.SunEvents(32131132, 1231124),
                1,
                requestTime
            ),
            ApiModel.WeatherList(
                ApiModel.WeatherList.Main(82, 762, 16.0f),
                listOf(ApiModel.WeatherList.Weather("", "", 1, "Rain")),
                ApiModel.WeatherList.Wind(36.0f),
                ApiModel.WeatherList.SunEvents(32131132, 1231124),
                2,
                requestTime
            ),
            ApiModel.WeatherList(
                ApiModel.WeatherList.Main(44, 762, 35.0f),
                listOf(ApiModel.WeatherList.Weather("", "", 1, "Sunny")),
                ApiModel.WeatherList.Wind(14.0f),
                ApiModel.WeatherList.SunEvents(32131132, 1231124),
                3,
                requestTime
            ),
            ApiModel.WeatherList(
                ApiModel.WeatherList.Main(67, 762, 15.0f),
                listOf(ApiModel.WeatherList.Weather("", "", 1, "Windy")),
                ApiModel.WeatherList.Wind(65.0f),
                ApiModel.WeatherList.SunEvents(32131132, 1231124),
                4,
                requestTime
            ),
            ApiModel.WeatherList(
                ApiModel.WeatherList.Main(67, 762, 9.0f),
                listOf(ApiModel.WeatherList.Weather("", "", 1, "Storm")),
                ApiModel.WeatherList.Wind(87.0f),
                ApiModel.WeatherList.SunEvents(32131132, 1231124),
                5,
                requestTime
            )
        )

        val forecastList = listOf(
            Forecast(1L, "Baku", R.drawable.baku_maiden_tower, "Clouds", 14, 762, 67, 28f, 32131132, 1231124, requestTime),
            Forecast(2L, "Sumgait", R.drawable.sumgait_industrial_city, "Rain", 16, 762, 82, 36f, 32131132, 1231124, requestTime),
            Forecast(3L, "Lenkaran", R.drawable.lenkaran_samovar, "Sunny", 35, 762, 44, 14f, 32131132, 1231124, requestTime),
            Forecast(4L, "Shamakhi", R.drawable.shamakhi_observatory_city, "Windy", 15, 762, 67, 65f, 32131132, 1231124, requestTime),
            Forecast(5L, "Goychay", R.drawable.baku_maiden_tower, "Storm", 9, 762, 67, 87f, 32131132, 1231124, requestTime)
        )

        assertThat(apiModelWeatherList.toForecast(), `is`(equalTo(forecastList)))
    }
}