package com.talmir.weatherlogger.domain.screens.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.talmir.weatherlogger.R
import com.talmir.weatherlogger.databinding.FragmentCityForecastDetailsBinding
import com.talmir.weatherlogger.helpers.Fragment
import com.talmir.weatherlogger.helpers.cityNameById

class CityForecastDetailsFragment : Fragment<CityForecastDetailsViewModel>() {

    override val viewModelType: Class<CityForecastDetailsViewModel>
        get() = CityForecastDetailsViewModel::class.java

    override val cityId: Long
        get() = CityForecastDetailsFragmentArgs.fromBundle(requireArguments()).cityId

    private lateinit var binding: FragmentCityForecastDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.theme?.applyStyle(R.style.CityForecastDetailsDialogTheme, false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCityForecastDetailsBinding.inflate(inflater)

        binding.toolbar.run {
            setNavigationIcon(R.drawable.ic_close)
            setNavigationOnClickListener {
                findNavController().navigate(
                    CityForecastDetailsFragmentDirections.actionFragmentCityForecastDetailsToFragmentHome()
                )
            }
            title = "Forecast data for ${cityId.cityNameById()}"
            setTitleTextColor(resources.getColor(R.color.white, null))
        }

        viewModel.cityForecastDetails.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                println("No data for ${cityId.cityNameById()}")
            } else {
                val adapter = CityForecastDetailsAdapter(it.reversed())
                binding.cityForecastDataRecycler.adapter = adapter
            }
        })

        return binding.root
    }
}
