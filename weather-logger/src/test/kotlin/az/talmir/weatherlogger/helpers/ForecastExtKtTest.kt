package az.talmir.weatherlogger.helpers

import az.talmir.weatherlogger.R
import az.talmir.weatherlogger.data.source.local.room.cities.CityEntity
import az.talmir.weatherlogger.data.source.local.room.city_forecast_data.CityForecastData
import az.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity
import az.talmir.weatherlogger.data.source.remote.network.ApiModel
import az.talmir.weatherlogger.helpers.weather.Forecast
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
            ForecastDataEntity(Constants.BAKU_CITY_ID, 802, 14, 762, 67, 28f, 32131132, 1231124, requestTime),
            ForecastDataEntity(Constants.SUMGAIT_CITY_ID, 511, 16, 762, 82, 36f, 32131132, 1231124, requestTime),
            ForecastDataEntity(Constants.LENKARAN_CITY_ID, 622, 35, 762, 44, 14f, 32131132, 1231124, requestTime),
            ForecastDataEntity(Constants.SHAMAKHI_CITY_ID, 701, 15, 762, 67, 65f, 32131132, 1231124, requestTime),
            ForecastDataEntity(Constants.GOYCHAY_CITY_ID, 200, 9, 762, 67, 87f, 32131132, 1231124, requestTime)
        )

        val forecastList = listOf(
            Forecast(Constants.BAKU_CITY_ID,"Baku", R.drawable.baku_maiden_tower, 802, 14, (762 * 0.750_061_683).toInt(), 67, 28f, 32131132, 1231124, requestTime),
            Forecast(Constants.SUMGAIT_CITY_ID,"Sumgait", R.drawable.sumgait_industrial_city, 511, 16, (762 * 0.750_061_683).toInt(), 82, 36f, 32131132, 1231124, requestTime),
            Forecast(Constants.LENKARAN_CITY_ID,"Lenkaran", R.drawable.lenkaran_samovar, 622, 35, (762 * 0.750_061_683).toInt(), 44, 14f, 32131132, 1231124, requestTime),
            Forecast(Constants.SHAMAKHI_CITY_ID,"Shamakhi", R.drawable.shamakhi_observatory_city, 701, 15, (762 * 0.750_061_683).toInt(), 67, 65f, 32131132, 1231124, requestTime),
            Forecast(Constants.GOYCHAY_CITY_ID,"Goychay", R.drawable.goychay_pomegranate_city, 200, 9, (762 * 0.750_061_683).toInt(), 67, 87f, 32131132, 1231124, requestTime)
        )

        assertThat(forecastEntityList.toForecast(), `is`(equalTo(forecastList)))
    }

    @Test
    fun toForecast_apiModelWeatherList_returnsForecastList() {
        val requestTime = Date()

        val apiModelWeatherList = listOf(
            ApiModel.WeatherList(
                ApiModel.WeatherList.Main(67, 762, 14.0f),
                listOf(ApiModel.WeatherList.Weather(802)),
                ApiModel.WeatherList.Wind(28.0f),
                ApiModel.WeatherList.SunEvents(32131132, 1231124),
                587084,
                requestTime
            ),
            ApiModel.WeatherList(
                ApiModel.WeatherList.Main(82, 762, 16.0f),
                listOf(ApiModel.WeatherList.Weather(511)),
                ApiModel.WeatherList.Wind(36.0f),
                ApiModel.WeatherList.SunEvents(32131132, 1231124),
                584923,
                requestTime
            ),
            ApiModel.WeatherList(
                ApiModel.WeatherList.Main(44, 762, 35.0f),
                listOf(ApiModel.WeatherList.Weather(622)),
                ApiModel.WeatherList.Wind(14.0f),
                ApiModel.WeatherList.SunEvents(32131132, 1231124),
                147613,
                requestTime
            ),
            ApiModel.WeatherList(
                ApiModel.WeatherList.Main(67, 762, 15.0f),
                listOf(ApiModel.WeatherList.Weather(701)),
                ApiModel.WeatherList.Wind(65.0f),
                ApiModel.WeatherList.SunEvents(32131132, 1231124),
                585156,
                requestTime
            ),
            ApiModel.WeatherList(
                ApiModel.WeatherList.Main(67, 762, 9.0f),
                listOf(ApiModel.WeatherList.Weather(200)),
                ApiModel.WeatherList.Wind(87.0f),
                ApiModel.WeatherList.SunEvents(32131132, 1231124),
                586482,
                requestTime
            )
        )

        val forecastList = listOf(
            Forecast(Constants.BAKU_CITY_ID, "Baku", R.drawable.baku_maiden_tower, 802, 14, (762 * 0.750_061_683).toInt(), 67, 28f, 32131132, 1231124, requestTime),
            Forecast(Constants.SUMGAIT_CITY_ID, "Sumgait", R.drawable.sumgait_industrial_city, 511, 16, (762 * 0.750_061_683).toInt(), 82, 36f, 32131132, 1231124, requestTime),
            Forecast(Constants.LENKARAN_CITY_ID, "Lenkaran", R.drawable.lenkaran_samovar, 622, 35, (762 * 0.750_061_683).toInt(), 44, 14f, 32131132, 1231124, requestTime),
            Forecast(Constants.SHAMAKHI_CITY_ID, "Shamakhi", R.drawable.shamakhi_observatory_city, 701, 15, (762 * 0.750_061_683).toInt(), 67, 65f, 32131132, 1231124, requestTime),
            Forecast(Constants.GOYCHAY_CITY_ID, "Goychay", R.drawable.goychay_pomegranate_city, 200, 9, (762 * 0.750_061_683).toInt(), 67, 87f, 32131132, 1231124, requestTime)
        )

        assertThat(apiModelWeatherList.toForecast(), `is`(equalTo(forecastList)))
    }

    @Test
    fun toForecast_cityForecastData_returnsForecastList() {
        val requestTime = Date()
        val city = CityEntity(Constants.BAKU_CITY_ID, "BAKU")
        val forecastEntityList = listOf(
            ForecastDataEntity(Constants.BAKU_CITY_ID, 802, 14, 762, 67, 28f, 32131132, 1231124, requestTime),
            ForecastDataEntity(Constants.BAKU_CITY_ID, 511, 16, 762, 82, 36f, 32131132, 1231124, requestTime),
            ForecastDataEntity(Constants.BAKU_CITY_ID, 622, 35, 762, 44, 14f, 32131132, 1231124, requestTime),
            ForecastDataEntity(Constants.BAKU_CITY_ID, 701, 15, 762, 67, 65f, 32131132, 1231124, requestTime),
            ForecastDataEntity(Constants.BAKU_CITY_ID, 200, 9, 762, 67, 87f, 32131132, 1231124, requestTime)
        )
        val cityForecastData = CityForecastData(city, forecastEntityList)

        val forecastList = listOf(
            Forecast(Constants.BAKU_CITY_ID, "Baku", R.drawable.baku_maiden_tower, 802, 14, (762 * 0.750_061_683).toInt(), 67, 28f, 32131132, 1231124, requestTime),
            Forecast(Constants.BAKU_CITY_ID, "Baku", R.drawable.baku_maiden_tower, 511, 16, (762 * 0.750_061_683).toInt(), 82, 36f, 32131132, 1231124, requestTime),
            Forecast(Constants.BAKU_CITY_ID, "Baku", R.drawable.baku_maiden_tower, 622, 35, (762 * 0.750_061_683).toInt(), 44, 14f, 32131132, 1231124, requestTime),
            Forecast(Constants.BAKU_CITY_ID, "Baku", R.drawable.baku_maiden_tower, 701, 15, (762 * 0.750_061_683).toInt(), 67, 65f, 32131132, 1231124, requestTime),
            Forecast(Constants.BAKU_CITY_ID, "Baku", R.drawable.baku_maiden_tower, 200, 9, (762 * 0.750_061_683).toInt(), 67, 87f, 32131132, 1231124, requestTime)
        )

        assertThat(cityForecastData.toForecast(), `is`(forecastList))
    }
}
