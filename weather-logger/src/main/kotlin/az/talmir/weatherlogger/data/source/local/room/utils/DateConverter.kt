package az.talmir.weatherlogger.data.source.local.room.utils

import androidx.room.TypeConverter
import java.util.Date

/**
 * A type converter object:
 * converts incoming date to long and vice versa.
 *
 * @author mirjalal
 */
class DateConverter {
    @TypeConverter
    fun dateToLong(date: Date?) =
        date?.time

    @TypeConverter
    fun longToDate(time: Long?) =
        if (time == null)
            null
        else
            Date(time)
}
