package com.talmir.weatherlogger.data.source.local.room.forecast_data

import androidx.room.*
import com.talmir.weatherlogger.data.source.local.room.utils.DateConverter
import java.util.Date

@Entity(tableName = "forecast_data")
@TypeConverters(DateConverter::class)
data class ForecastDataEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "forecast_data_id") var id: Int,
    @ColumnInfo(name = "type") var weatherName: String, // cloudy, clear sky etc
    @ColumnInfo(name = "temperature") var weatherTemperature: Int, // in Celsius
    @ColumnInfo(name = "pressure") var weatherPressure: Int, // mmHg; 1 hPa to mmHg = 0.75006 mmHg
    @ColumnInfo(name = "humidity") var weatherHumidity: Int, // %
    @ColumnInfo(name = "wind_speed") var weatherWindSpeed: Float,
    @Ignore var sunrise: Int,
    @Ignore var sunset: Int,
    @ColumnInfo(name = "timestamp") var requestTime: Date = Date()
) {
    constructor() : this(0, "", 0, 0, 0, 0f, 0, 0)
}
