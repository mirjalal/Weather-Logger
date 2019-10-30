package com.talmir.weatherlogger.helpers

import androidx.fragment.app.viewModels
import com.talmir.weatherlogger.data.ForecastsRepository
import com.talmir.weatherlogger.domain.screens.home.HomeViewModel

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

//    val viewModel = viewModels<HomeViewModel> {
//        ViewModelFactory(ForecastsRepository())
//    }

//    val viewModel = ViewModelProvider(.of(this, ViewModelFactory(repository)).get(viewModelClass)
}