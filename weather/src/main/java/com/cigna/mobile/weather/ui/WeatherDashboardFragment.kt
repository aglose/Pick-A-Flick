package com.cigna.mobile.weather.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cigna.mobile.weather.data.LatLng
import com.cigna.mobile.weather.R
import com.cigna.mobile.weather.databinding.WeatherImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_weather_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class WeatherDashboardFragment : Fragment() {
    private val viewModel : WeatherViewModel by viewModel()

    private lateinit var binding: WeatherImpl

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_weather_dashboard, container, false)
        binding = WeatherImpl(null, rootView)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.subscribeToErrors().observe(this, Observer {
            Snackbar.make(view, "Error occurred: $it", Snackbar.LENGTH_LONG).show()
        })

        viewModel.subscribeToLoading().observe(this, Observer {
            progress_bar.visibility = if(it) VISIBLE else GONE
        })

        viewModel.getWeatherForLatLng(LatLng(39.946632, -75.172134)).observe(this, Observer {
//            texting_123.text = it.properties.forecast
            binding.setWeather(it.getWeather())
        })
    }
}