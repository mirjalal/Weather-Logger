package com.talmir.weatherlogger.domain.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.talmir.weatherlogger.data.IForecastsRepository
import com.talmir.weatherlogger.data.Result

class HomeViewModel(
    private val forecastRepository: IForecastsRepository
) : ViewModel() {
    val forecastData = liveData {
        when (val data = forecastRepository.getForecastData()) {
            is Result.Success -> emit(data.data)
            else -> emit(listOf())
        }
    }
}
