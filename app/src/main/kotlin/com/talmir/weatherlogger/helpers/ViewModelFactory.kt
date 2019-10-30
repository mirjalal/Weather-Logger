package com.talmir.weatherlogger.helpers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.talmir.weatherlogger.data.ForecastsRepository
import com.talmir.weatherlogger.domain.screens.details.CityForecastDetailsViewModel
import com.talmir.weatherlogger.domain.screens.home.HomeViewModel

class ViewModelFactory(
    private val forecastRepository: ForecastsRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(HomeViewModel::class.java) ->
                HomeViewModel(forecastRepository)
            isAssignableFrom(CityForecastDetailsViewModel::class.java) ->
                CityForecastDetailsViewModel(forecastRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
