package by.startandroid.weatherforecastdemo.ui.weather

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import by.startandroid.weatherforecastdemo.R
import by.startandroid.weatherforecastdemo.databinding.FragmentWeatherBinding
import by.startandroid.weatherforecastdemo.ui.MainActivity
import by.startandroid.weatherforecastdemo.viewmodel.ViewModel

class WeatherFragment : Fragment() {
    private var binding: FragmentWeatherBinding? = null
    private lateinit var navController: NavController
    private lateinit var viewModel: ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false)
        viewModel = ViewModelProvider(activity as MainActivity).get(ViewModel::class.java)
        viewModel.getAllName()
        viewModel.getWeather()
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        viewModel.weatherDataLive.observe(activity as MainActivity, Observer {
            it?.let { weather ->
                binding?.name?.text = weather.name
                binding?.temp?.text = weather.main.temp.toInt().toString()
                binding?.speed?.text = weather.wind.speed.toString()
                binding?.feelsLike?.text = weather.main.feels_like.toInt().toString()
                binding?.pressure?.text = weather.main.pressure.toString()
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}