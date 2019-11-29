package com.talmir.weatherlogger.domain.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.talmir.weatherlogger.data.ForecastsRepository

class HomeViewModel(
    forecastRepository: ForecastsRepository
) : ViewModel() {

    val forecastData = liveData {
        emit(forecastRepository.getForecastData())
    }
}
