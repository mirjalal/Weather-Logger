package com.talmir.weatherlogger.helpers

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.talmir.weatherlogger.WeatherLoggerApp
import com.talmir.weatherlogger.data.ForecastsRepository

abstract class Fragment<ViewModel : androidx.lifecycle.ViewModel> : androidx.fragment.app.Fragment() {
    /**
     * If exits, visit the links below, and read them carefully. Then you might
     * understand why I write this abstract val to be overridden in fragments...
     *
     * Read this question and its answer: https://stackoverflow.com/q/34122450
     * And this comment: https://stackoverflow.com/q/34122450/#comment81784441_34463352
     * And this post with its all replies: https://stackoverflow.com/q/55289334/
     */
    abstract val viewModelType: Class<ViewModel>

    private lateinit var repository: ForecastsRepository

    lateinit var viewModel: ViewModel
        private set

    override fun onAttach(context: Context) {
        super.onAttach(context)

        repository = (requireActivity().application as WeatherLoggerApp).forecastsRepository
        viewModel = ViewModelProvider(this, ViewModelFactory(repository))[viewModelType]
    }
}
