package com.talmir.weatherlogger.helpers

import com.talmir.weatherlogger.R
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import com.talmir.weatherlogger.data.source.remote.network.ApiModel
import com.talmir.weatherlogger.helpers.weather.Forecast
import org.junit.Test
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat

class ForecastExtKtTest {

    @Test
    fun toForecast_forecastEntityList_returnsForecastList() {
        val forecastEntityList = listOf(
            ForecastDataEntity(1L, "Clouds", 14, 762, 67, 28f, 32131132, 1231124),
            ForecastDataEntity(2L, "Rain", 16, 762, 82, 36f, 32131132, 1231124),
            ForecastDataEntity(3L, "Sunny", 35, 762, 44, 14f, 32131132, 1231124),
            ForecastDataEntity(4L, "Windy", 15, 762, 67, 65f, 32131132, 1231124),
            ForecastDataEntity(5L, "Storm", 9, 762, 67, 87f, 32131132, 1231124)
        )

        val forecastList = listOf(
            Forecast(1L,"Baku", R.drawable.baku_maiden_tower, "Clouds", 14, 762, 67, 28f, 32131132, 1231124),
            Forecast(2L,"Sumgait", R.drawable.sumgait_industrial_city, "Rain", 16, 762, 82, 36f, 32131132, 1231124),
            Forecast(3L,"Lenkaran", R.drawable.lenkaran_samovar, "Sunny", 35, 762, 44, 14f, 32131132, 1231124),
            Forecast(4L,"Shamakhi", R.drawable.shamakhi_observatory_city, "Windy", 15, 762, 67, 65f, 32131132, 1231124),
            Forecast(5L,"Goychay", R.drawable.baku_maiden_tower, "Storm", 9, 762, 67, 87f, 32131132, 1231124)
        )

        assertThat(forecastEntityList.toForecast(), `is`(equalTo(forecastList)))
    }

    @Test
    fun toForecast_weatherList_returnsForecastList() {
        val apiModelWeatherList = listOf(
            ApiModel.WeatherList(
                ApiModel.WeatherList.Clouds(75),
                ApiModel.WeatherList.Main(67, 762, 14.0, 0, 0),
                "Baku",
                ApiModel.WeatherList.Sys("", 32131132, 1231124, 0),
                listOf(ApiModel.WeatherList.Weather("", "", 1, "Clouds")),
                ApiModel.WeatherList.Wind(4, 28.0),
                1
            ),
            ApiModel.WeatherList(
                ApiModel.WeatherList.Clouds(75),
                ApiModel.WeatherList.Main(82, 762, 16.0, 0, 0),
                "Sumgait",
                ApiModel.WeatherList.Sys("", 32131132, 1231124, 0),
                listOf(ApiModel.WeatherList.Weather("", "", 1, "Rain")),
                ApiModel.WeatherList.Wind(4, 36.0),
                2
            ),
            ApiModel.WeatherList(
                ApiModel.WeatherList.Clouds(75),
                ApiModel.WeatherList.Main(44, 762, 35.0, 0, 0),
                "Lenkaran",
                ApiModel.WeatherList.Sys("", 32131132, 1231124, 0),
                listOf(ApiModel.WeatherList.Weather("", "", 1, "Sunny")),
                ApiModel.WeatherList.Wind(4, 14.0),
                3
            ),
            ApiModel.WeatherList(
                ApiModel.WeatherList.Clouds(75),
                ApiModel.WeatherList.Main(67, 762, 15.0, 0, 0),
                "Shamakhi",
                ApiModel.WeatherList.Sys("", 32131132, 1231124, 0),
                listOf(ApiModel.WeatherList.Weather("", "", 1, "Windy")),
                ApiModel.WeatherList.Wind(4, 65.0),
                4
            ),
            ApiModel.WeatherList(
                ApiModel.WeatherList.Clouds(75),
                ApiModel.WeatherList.Main(67, 762, 9.0, 0, 0),
                "Goychay",
                ApiModel.WeatherList.Sys("", 32131132, 1231124, 0),
                listOf(ApiModel.WeatherList.Weather("", "", 1, "Storm")),
                ApiModel.WeatherList.Wind(4, 87.0),
                5
            )
        )

        val forecastList = listOf(
            Forecast(1L, "Baku", R.drawable.baku_maiden_tower, "Clouds", 14, 762, 67, 28f, 32131132, 1231124),
            Forecast(2L, "Sumgait", R.drawable.sumgait_industrial_city, "Rain", 16, 762, 82, 36f, 32131132, 1231124),
            Forecast(3L, "Lenkaran", R.drawable.lenkaran_samovar, "Sunny", 35, 762, 44, 14f, 32131132, 1231124),
            Forecast(4L, "Shamakhi", R.drawable.shamakhi_observatory_city, "Windy", 15, 762, 67, 65f, 32131132, 1231124),
            Forecast(5L, "Goychay", R.drawable.baku_maiden_tower, "Storm", 9, 762, 67, 87f, 32131132, 1231124)
        )

        assertThat(apiModelWeatherList.toForecast(), `is`(equalTo(forecastList)))
    }
}
