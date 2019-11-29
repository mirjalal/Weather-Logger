package com.talmir.weatherlogger.helpers

fun Long.cityNameById() = when (this) {
    1L -> "Baku"
    2L -> "Sumgait"
    3L -> "Lenkaran"
    4L -> "Shamakhi"
    else -> "Goychay"
}

fun Long.cityIconById() = when (this) {
    1L -> com.talmir.weatherlogger.R.drawable.baku_maiden_tower
    2L -> com.talmir.weatherlogger.R.drawable.sumgait_industrial_city
    3L -> com.talmir.weatherlogger.R.drawable.lenkaran_samovar
    4L -> com.talmir.weatherlogger.R.drawable.shamakhi_observatory_city
    else -> com.talmir.weatherlogger.R.drawable.baku_maiden_tower
}
