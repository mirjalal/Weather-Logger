package com.talmir.weatherlogger.helpers

fun Long.cityNameById() = when (this) {
    1L, 587084L -> "Baku"
    2L, 584923L -> "Sumgait"
    3L, 147613L -> "Lenkaran"
    4L, 585156L -> "Shamakhi"
    else -> "Goychay"
}

fun Long.cityIconById() = when (this) {
    1L, 587084L -> com.talmir.weatherlogger.R.drawable.baku_maiden_tower
    2L, 584923L -> com.talmir.weatherlogger.R.drawable.sumgait_industrial_city
    3L, 147613L -> com.talmir.weatherlogger.R.drawable.lenkaran_samovar
    4L, 585156L -> com.talmir.weatherlogger.R.drawable.shamakhi_observatory_city
    else -> com.talmir.weatherlogger.R.drawable.baku_maiden_tower
}
