package az.talmir.weatherlogger.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import az.talmir.weatherlogger.data.IForecastRepository
import az.talmir.weatherlogger.data.Result

class CityForecastDetailsViewModel(
    forecastRepository: IForecastRepository,
    cityId: Long
) : ViewModel() {
    val cityForecastDetails = liveData {
        when (val data = forecastRepository.getSingleCityForecastData(cityId)) {
            is Result.Success -> emit(data.data)
            else -> emit(listOf())
        }
    }
}
