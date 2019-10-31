package com.talmir.weatherlogger.data.source.local.room.city_forecast_data

import androidx.room.*
import com.talmir.weatherlogger.data.source.local.room.cities.CityEntity
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity

@Entity(
    tableName = "city_forecast_data",
    indices = [Index("forecast_data_id"), Index("city_id")],
    foreignKeys = [
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = ["city_id"],
            childColumns = ["city_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ForecastDataEntity::class,
            parentColumns = ["forecast_data_id"],
            childColumns = ["forecast_data_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class CityForecastDataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "city_id") val cityId: Int,
    @ColumnInfo(name = "forecast_data_id") val forecastDataId: Int
) {
    constructor() : this(0,  0, 0)
}
