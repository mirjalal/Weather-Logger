package com.talmir.weatherlogger.domain.screens.details

import com.talmir.weatherlogger.helpers.Fragment

class CityForecastDetailsFragment : Fragment<CityForecastDetailsViewModel>() {

    override val viewModelType: Class<CityForecastDetailsViewModel>
        get() = CityForecastDetailsViewModel::class.java


}