package com.talmir.weatherlogger.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.talmir.weatherlogger.data.IForecastRepository
import com.talmir.weatherlogger.data.Result

class HomeViewModel(
    private val forecastRepository: IForecastRepository
) : ViewModel() {
    val forecastData = liveData {
        when (val data = forecastRepository.getForecastData()) {
            is Result.Success -> emit(data.data)
            else -> emit(listOf())
        }
    }
}
