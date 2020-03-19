package az.talmir.weatherlogger.koin

import az.talmir.weatherlogger.ServiceLocator
import az.talmir.weatherlogger.ui.details.CityForecastDetailsViewModel
import az.talmir.weatherlogger.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {
    // ViewModel instance of HomeViewModel
    // get() will resolve IForecastsRepository instance
    viewModel { HomeViewModel(get()) }

    // ViewModel instance of CityForecastDetailsViewModel
    // get() will resolve IForecastsRepository instance
    // and cityId will be passed in its fragment class
    viewModel { (cityId: Long) -> CityForecastDetailsViewModel(get(), cityId) }

    // single instance of IForecastsRepository
    single { ServiceLocator.provideForecastRepository(androidContext()) }
}
