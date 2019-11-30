package com.talmir.weatherlogger.domain.screens.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.talmir.weatherlogger.R
import com.talmir.weatherlogger.databinding.ForecastDataRecyclerItemBinding
import com.talmir.weatherlogger.helpers.weather.Forecast
import java.util.Locale
import org.ocpsoft.prettytime.PrettyTime

/**
 * A [RecyclerView] adapter file that implements
 * [ListAdapter] class.
 *
 * @author Mirjalal
 * @since 11/30/2019
 */
class CityForecastDetailsAdapter(cityData: List<Forecast>) :
    RecyclerView.Adapter<CityForecastDetailsAdapter.CityForecastDetailsAdapterViewHolder>() {

    private val cityDataLocalCache = cityData

    companion object {
        private val prettyTime = PrettyTime()
    }

    override fun getItemCount() =
        cityDataLocalCache.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CityForecastDetailsAdapterViewHolder.from(parent)

    override fun onBindViewHolder(holder: CityForecastDetailsAdapterViewHolder, position: Int) =
        holder.bind(cityDataLocalCache[position])

    class CityForecastDetailsAdapterViewHolder private constructor(private val binding: ForecastDataRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(forecastData: Forecast) {
            // set binding variables if any...
            binding.weatherMeasureTime.text = prettyTime.format(forecastData.requestTime)
            binding.weatherTemperature.text = String.format(Locale.getDefault(), "%d", forecastData.temperature)
            binding.weatherPressure.text = String.format(Locale.getDefault(), "%d", forecastData.pressure)
            binding.weatherHumidity.text = String.format(Locale.getDefault(), "%d", forecastData.humidity)
            binding.weatherWindSpeed.text = String.format(Locale.getDefault(), "%.2f", forecastData.windSpeed)
            binding.termometer.setImageResource(R.drawable.ic_termometer)
            binding.barometer.setImageResource(R.drawable.ic_barometer)
            binding.psychrometer.setImageResource(R.drawable.ic_psychrometer)
            binding.anemometer.setImageResource(R.drawable.ic_wind_speed)
//            binding.weatherIcon.setImageResource(forecastData.weatherType)

            /**
             * causes the properties updates to execute immediately.
             * since I'm calling [bind] from [onBindViewHolder]
             * having the bindings execute immediately. as a practice
             * can prevent the recycler view from having to perform
             * extra calculations when it figures out how to display
             * the list.
             */
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CityForecastDetailsAdapterViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ForecastDataRecyclerItemBinding.inflate(layoutInflater, parent, false)

                return CityForecastDetailsAdapterViewHolder(binding)
            }
        }
    }
}