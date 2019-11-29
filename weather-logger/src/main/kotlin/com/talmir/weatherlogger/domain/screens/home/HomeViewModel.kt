package com.talmir.weatherlogger.domain.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talmir.weatherlogger.data.ForecastsRepository
import com.talmir.weatherlogger.data.Result
import com.talmir.weatherlogger.helpers.weather.Forecast
import kotlinx.coroutines.launch

class HomeViewModel(
    private val forecastRepository: ForecastsRepository
) : ViewModel() {



    fun getForecastData() {
        viewModelScope.launch {
            forecastRepository.getForecastData().let {
                if (it is Result.Success)
                    onDataLoaded(it.data)
                else
                    onDataLoaded(listOf())
            }
        }
    }

    private fun onDataLoaded(data: List<Forecast>) {

    }
}