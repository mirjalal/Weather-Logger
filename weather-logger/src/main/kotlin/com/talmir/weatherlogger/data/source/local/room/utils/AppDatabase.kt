package com.talmir.weatherlogger.data.source.local.room.utils

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.talmir.weatherlogger.data.source.local.room.cities.CityDao
import com.talmir.weatherlogger.data.source.local.room.cities.CityEntity
import com.talmir.weatherlogger.data.source.local.room.city_forecast_data.CityForecastDataDao
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataDao
import com.talmir.weatherlogger.data.source.local.room.forecast_data.ForecastDataEntity

/**
 * This is the backend. The database. This used to be done by the OpenHelper.
 * The fact that this has very few comments emphasizes its coolness.
 */
@Database(
    entities = [CityEntity::class, ForecastDataEntity::class],
    version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun cityForecastDataDao(): CityForecastDataDao
    abstract fun forecastDataDao(): ForecastDataDao

    /**
     * Define a companion object, this allows us to add functions on the SleepDatabase class.
     */
    companion object {
        // DATABASE_INSTANCE will keep a reference to any database returned via getInstance.
        //
        // This will help us avoid repeatedly initializing the database, which is expensive.
        //
        // The value of a volatile variable will never be cached, and all writes and
        // reads will be done to and from the main memory. It means that changes made by one
        // thread to shared data are visible to other threads.
        @Volatile
        private var DATABASE_INSTANCE: AppDatabase? = null

        /**
         * Helper function to get the database.
         *
         * If a database has already been retrieved, the previous database will be returned.
         * Otherwise, create a new database.
         *
         * This function is threadsafe, and callers should cache the result for multiple database
         * calls to avoid overhead.
         *
         * This is an example of a simple Singleton pattern that takes another Singleton as an
         * argument in Kotlin.
         *
         * To learn more about Singleton read the wikipedia article:
         * https://en.wikipedia.org/wiki/Singleton_pattern
         *
         * @param application used to get access to the filesystem.
         */
        fun getDatabase(application: Application): AppDatabase {
            val tempInstance = DATABASE_INSTANCE
            if (tempInstance != null)
                return tempInstance

            // Multiple threads can ask for the database at the same time, ensure we only initialize
            // it once by using synchronized. Only one thread may enter a synchronized block at a
            // time.
            synchronized(this) {
                val instance = Room
                    .databaseBuilder(
                        application.applicationContext,
                        AppDatabase::class.java,
                        "weather_logger_db")
                    .build()
                DATABASE_INSTANCE = instance
                return instance
            }
        }
    }
}
