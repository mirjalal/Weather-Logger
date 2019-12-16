package com.talmir.weatherlogger.helpers

object Constants {
    private const val API_URL = "https://api.openweathermap.org/data/2.5/"
    private const val API_KEY = "d64bc1686044c643b68f9573b2a5cea2"

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

    private const val BAKU = 587084L
    private const val SUMGAIT = 584923L
    private const val LENKARAN = 147613L
    private const val SHAMAKHI = 585156L
    private const val GOYCHAY = 586482L

    fun buildApiUrl() = StringBuilder()
        .append(API_URL).append("group?id=")
        .append(BAKU).append(',')
        .append(SUMGAIT).append(',')
        .append(LENKARAN).append(',')
        .append(SHAMAKHI).append(',')
        .append(GOYCHAY).append("&units=metric&appid=")
        .append(API_KEY).toString()
}
