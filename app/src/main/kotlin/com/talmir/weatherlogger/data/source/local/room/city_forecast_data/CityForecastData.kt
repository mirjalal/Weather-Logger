package com.talmir.weatherlogger.data.source.local.room.city_forecast_data

import com.talmir.weatherlogger.data.source.local.room.cities.CityEntity
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity

import androidx.room.Embedded
import androidx.room.Relation
import com.talmir.weatherlogger.helpers.toForecast

data class CityForecastData(
    @Embedded val city: CityEntity,
    @Relation(
        parentColumn = "city_id",
        entity = ForecastDataEntity::class,
        entityColumn = "city_id"
    )
    val cityForecastData: List<ForecastDataEntity>
)
