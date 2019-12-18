package com.talmir.weatherlogger.domain.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.talmir.weatherlogger.data.IForecastsRepository
import com.talmir.weatherlogger.data.Result

class CityForecastDetailsViewModel(
    forecastRepository: IForecastsRepository,
    cityId: Long
) : ViewModel() {
    val cityForecastDetails = liveData {
        when (val data = forecastRepository.getCityForecastData(cityId)) {
            is Result.Success -> emit(data.data)
            else -> emit(listOf())
        }
    }
}
