package com.cigna.mobile.weather.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.cigna.mobile.weather.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class WeatherDashboardFragment : Fragment() {
    private val viewModel : WeatherViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getWeatherForLatLng(39.946632, -75.172134).observe(this, Observer {
            Log.d(WeatherDashboardFragment::class.java.simpleName, "$it")
        })
    }
}