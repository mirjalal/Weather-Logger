package com.talmir.weatherlogger.domain.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.talmir.weatherlogger.R
import com.talmir.weatherlogger.databinding.FragmentHomeBinding
import com.talmir.weatherlogger.helpers.Constants
import com.talmir.weatherlogger.helpers.weather.Forecast
import com.talmir.weatherlogger.helpers.weather.ForecastAdapter
import com.talmir.weatherlogger.helpers.weather.WeatherStation
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlin.math.abs

class HomeFragment : Fragment(),
    DiscreteScrollView.ScrollStateChangeListener<ForecastAdapter.ForecastViewHolder>,
    DiscreteScrollView.OnItemChangedListener<ForecastAdapter.ForecastViewHolder> {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var forecasts: List<Forecast>
    private var cityIndex = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this

        // TODO: make network request
        // TODO: get response
        // TODO: call WeatherStation.createForecast()
        // TODO: call initializeView()

        return binding.root
    }

    private fun initializeView() {
        forecasts = WeatherStation.forecasts
        binding.forecastCityPicker.run {
            setSlideOnFling(true)
            adapter = ForecastAdapter(forecasts)
            addOnItemChangedListener(this@HomeFragment)
            addScrollStateChangeListener(this@HomeFragment)
            setItemTransitionTimeMillis(150)
            setItemTransformer(
                ScaleTransformer.Builder()
                    .setMinScale(0.8f)
                    .build())
        }
        binding.forecastView.setForecast(forecasts[0])
        binding.forecastView.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionFragmentHomeToFragmentCityForecastDetails(Constants.CITY_IDS[cityIndex])
            )
        }
    }

    override fun onScroll(position: Float, currentIndex: Int, newIndex: Int,
                          currentHolder: ForecastAdapter.ForecastViewHolder?,
                          newHolder: ForecastAdapter.ForecastViewHolder?) {
        val current: Forecast = forecasts[currentIndex]
        if (newIndex >= 0 && newIndex < (binding.forecastCityPicker.adapter?.itemCount ?: 0)) {
            val next: Forecast = forecasts[newIndex]
            binding.forecastView.onScroll(1f - abs(position), current, next)
        }
    }

    override fun onScrollEnd(holder: ForecastAdapter.ForecastViewHolder, position: Int) {
        cityIndex = position
    }

    override fun onScrollStart(holder: ForecastAdapter.ForecastViewHolder, position: Int) =
        holder.hideItem()

    override fun onCurrentItemChanged(viewHolder: ForecastAdapter.ForecastViewHolder?, position: Int) {
        if (viewHolder != null) {
            binding.forecastView.setForecast(forecasts[position])
            viewHolder.showItem()
        }
    }
}
