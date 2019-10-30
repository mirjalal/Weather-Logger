package com.talmir.weatherlogger.data.source.local.room.forecast_data

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Ignore
import androidx.room.Query
import com.talmir.weatherlogger.data.source.local.room.utils.BaseDao
import com.talmir.weatherlogger.data.source.local.room.utils.SingleCityForecastData

@Dao
abstract class ForecastDataDao : BaseDao<ForecastDataEntity> {

    @Query("SELECT * FROM forecast_data;")
    abstract suspend fun getWeatherData(): List<ForecastDataEntity>

    @Query("""
        SELECT fd.forecast_data_id AS forecastDataId, city_name AS cityName, type, temperature
        FROM forecast_data fd
        JOIN city_forecast_data cfd ON fd.forecast_data_id = cfd.forecast_data_id
        JOIN cities c ON cfd.city_id = c.city_id
        WHERE cfd.city_id = :cityId
        ORDER BY timestamp
        DESC LIMIT 1;""")
    abstract suspend fun getSingleCityForecastData(cityId: Int): List<SingleCityForecastData>

    @Query("""
        SELECT fd.forecast_data_id, type, temperature, pressure, humidity, wind_speed, timestamp
        FROM forecast_data fd
        JOIN city_forecast_data cfd ON fd.forecast_data_id = cfd.forecast_data_id
        WHERE cfd.city_id = :cityId
        ORDER BY timestamp
        DESC;""")
    abstract suspend fun getCityForecastData(cityId: Int): List<ForecastDataEntity>

    @Query("""
        SELECT fd.forecast_data_id, type as tt, temperature, pressure, humidity, wind_speed, timestamp
        FROM forecast_data fd
        JOIN city_forecast_data cfd ON fd.forecast_data_id = cfd.forecast_data_id
        ORDER BY timestamp
        DESC;""")
    abstract fun getAllForecastData(): Cursor

    @Query("""
        SELECT fd.forecast_data_id, type as  tt, temperature, pressure, humidity, wind_speed, timestamp
        FROM forecast_data fd
        JOIN city_forecast_data cfd ON fd.forecast_data_id = cfd.forecast_data_id
        WHERE city_id = :cityId
        ORDER BY timestamp
        DESC;""")
    abstract fun getForecastDataByCity(cityId: Int): Cursor
}
