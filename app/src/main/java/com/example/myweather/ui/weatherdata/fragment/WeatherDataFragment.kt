package com.example.myweather.ui.weatherdata.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

import com.example.myweather.databinding.FragmentWeatherDataBinding
import com.example.myweather.domain.model.WeatherData
import com.example.myweather.ui.weatherdata.viewmodel.WeatherDataViewModel
import com.example.myweather.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

import java.util.Locale

@AndroidEntryPoint
class WeatherDataFragment : Fragment() {
    lateinit var binding: FragmentWeatherDataBinding
 private val args: WeatherDataFragmentArgs by navArgs()
    val viewModel: WeatherDataViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lat=args.lat.toDouble()
        val lon=args.lon.toDouble()
        viewModel.getWeatherData(lat,lon)


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
   binding= FragmentWeatherDataBinding.inflate(inflater,container,false)
        return binding.root



    }
    private fun convertTimestampToTime(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        return format.format(date)
    }
    private fun loadWeatherIcon(iconCode: String?) {
        if (iconCode.isNullOrEmpty()) return

        val iconUrl = "https://openweathermap.org/img/wn/${iconCode}@4x.png"

        Glide.with(requireContext())
            .load(iconUrl)
            .into(binding.ivWeatherIcon)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.weatherDataFlowState.collect {value ->
                when(value){
                    is UIState.Error<*> -> {
                        Log.e(TAG,value.error.toString())
                    }
                    UIState.Loading -> {

                    }
                    is UIState.Success<*> -> {
                        val getWeatherData: WeatherData = value.data as WeatherData
                        Log.d(TAG,getWeatherData.toString())
                        binding.tvCityName.text=getWeatherData.name
                        binding.tvCountry.text=getWeatherData.sys.country
                        binding.tvWeatherDescription.text=getWeatherData.weather.get(0).description
                        binding.tvClouds.text=getWeatherData.clouds.all.toString() + "%"
                        binding.tvFeelsLike.text= "Feels like: ${getWeatherData.main.feels_like}°"

                        binding.tvHumidity.text = "${getWeatherData.main.humidity}%"
                        binding.tvWindSpeed.text = "${getWeatherData.wind.speed} m/s"
                        binding.tvPressure.text = "${getWeatherData.main.pressure} hPa"
                        binding.tvVisibility.text = "${getWeatherData.visibility / 1000} km"

                        binding.tvLatitude.text = getWeatherData.coord.lat.toString()
                        binding.tvLongitude.text = getWeatherData.coord.lon.toString()
                        binding.tvTimezone.text = "UTC${if (getWeatherData.timezone >= 0) "+" else ""}${getWeatherData.timezone / 3600}"
                        binding.tvSunrise.text = convertTimestampToTime(getWeatherData.sys.sunrise.toLong())
                        Log.d(TAG,convertTimestampToTime(getWeatherData.sys.sunrise.toLong()))
                        binding.tvLastUpdate.text = convertTimestampToTime(getWeatherData.dt.toLong())
                     loadWeatherIcon(getWeatherData.weather.get(0).icon!!)


                    }
                }

            }

        }

    }

 companion object{
     private const val TAG = "WeatherDataFragment"
 }
}