package az.talmir.weatherlogger.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import az.talmir.weatherlogger.data.IForecastRepository
import az.talmir.weatherlogger.data.Result

class HomeViewModel(
    private val forecastRepository: IForecastRepository
) : ViewModel() {
    /**
     * The lazy function has an argument with a default value
     * that controls its synchronization behaviour. If a lazy
     * property is accessed from multiple threads concurrently,
     * synchronization will need to be handled by choosing an
     * appropriate [LazyThreadSafetyMode].
     *
     * The default value [LazyThreadSafetyMode.SYNCHRONIZED]
     * will ensure only a single thread can initialize the
     * property using locks. If we are sure the property will
     * only be accessed by a single thread we can switch to
     * [LazyThreadSafetyMode.NONE] to avoid the overhead of
     * performing the synchronization. There is also the option
     * of using [LazyThreadSafetyMode.PUBLICATION] which allows
     * multiple threads to call the initializer, but only the
     * first returned value being used.
     *
     * Most UI code, such as in an Activity or Fragment, will
     * run on the UI thread and so properties that are only used
     * here can use the [LazyThreadSafetyMode.NONE]. We could
     * even add an extension to avoid specifying this each time.
     */
    private val forecastData by lazy(LazyThreadSafetyMode.NONE) {
        liveData {
            when (val data = forecastRepository.getForecastData()) {
                is Result.Success -> emit(data.data)
                else -> emit(listOf())
            }
        }
    }

    @JvmName("getForecasts")
    fun getForecastData() = forecastData
}
