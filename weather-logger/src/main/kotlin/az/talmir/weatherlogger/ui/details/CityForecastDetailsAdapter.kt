package az.talmir.weatherlogger.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import az.talmir.weatherlogger.databinding.ForecastDataRecyclerItemBinding
import az.talmir.weatherlogger.helpers.weather.Forecast
import az.talmir.weatherlogger.helpers.weatherToIcon
import org.ocpsoft.prettytime.PrettyTime

/**
 * A [RecyclerView] adapter file that implements [RecyclerView.Adapter] class.
 *
 * @author Mirjalal
 * @since 11/30/2019
 */
class CityForecastDetailsAdapter internal constructor(private val cityData: List<Forecast>) :
    RecyclerView.Adapter<CityForecastDetailsAdapter.CityForecastDetailsAdapterViewHolder>() {

    companion object {
        private val prettyTime = PrettyTime()
    }

    override fun getItemCount() =
        cityData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CityForecastDetailsAdapterViewHolder.from(parent)

    override fun onBindViewHolder(holder: CityForecastDetailsAdapterViewHolder, position: Int) =
        holder.bind(cityData[position])

    class CityForecastDetailsAdapterViewHolder private constructor(private val binding: ForecastDataRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(forecastData: Forecast) =
            with(binding) {
                prettyDate = prettyTime
                forecast = forecastData
                weatherIcon.setImageResource(forecastData.weatherType.weatherToIcon())

                /**
                 * causes the properties updates to execute immediately.
                 * since I'm calling [bind] from [onBindViewHolder]
                 * having the bindings execute immediately. as a practice
                 * can prevent the recycler view from having to perform
                 * extra calculations when it figures out how to display
                 * the list.
                 */
                executePendingBindings()
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
