package com.talmir.weatherlogger.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.talmir.weatherlogger.R
import com.talmir.weatherlogger.databinding.FragmentCityForecastDetailsBinding
import com.talmir.weatherlogger.helpers.cityNameById
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CityForecastDetailsFragment : Fragment() {

    private val cityId: Long
        get() = CityForecastDetailsFragmentArgs.fromBundle(requireArguments()).cityId

    private val viewModel : CityForecastDetailsViewModel by viewModel {
        parametersOf(cityId)
    }

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
            title = getString(R.string.city_forecast_data).format(cityId.cityNameById())
            setTitleTextColor(ContextCompat.getColor(context, R.color.white))
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