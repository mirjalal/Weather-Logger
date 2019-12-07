package com.talmir.weatherlogger.helpers

object Constants {
    private const val API_URL = "https://api.openweathermap.org/data/2.5/"
    private const val API_KEY = "a3f5cd4926414edbf39ba29587ff39ef"

    const val BAKU_CITY_ID = 1L
    const val SUMGAIT_CITY_ID = 2L
    const val LENKARAN_CITY_ID = 3L
    const val SHAMAKHI_CITY_ID = 4L
    const val GOYCHAY_CITY_ID = 5L
    val CITY_IDS = listOf(
        BAKU_CITY_ID,
        SUMGAIT_CITY_ID,
        LENKARAN_CITY_ID,
        SHAMAKHI_CITY_ID,
        GOYCHAY_CITY_ID
    )

    fun buildApiUrl() = StringBuilder()
        .append(API_URL)
        .append("group?id=")
        .append(BAKU_CITY_ID)
        .append(',')
        .append(SUMGAIT_CITY_ID)
        .append(',')
        .append(LENKARAN_CITY_ID)
        .append(',')
        .append(SHAMAKHI_CITY_ID)
        .append(',')
        .append(GOYCHAY_CITY_ID)
        .append("&units=metric&appid=")
        .append(API_KEY).toString()
}
