package com.example.myweather.ui.weatherdata.viewmodel

import android.provider.Contacts
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.domain.model.WeatherData
import com.example.myweather.domain.repo.WeatherDataRepository
import com.example.myweather.utils.API_KEY
import com.example.myweather.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherDataViewModel @Inject constructor (private val weatherDataRepository: WeatherDataRepository): ViewModel() {
    private  val TAG = "WeatherDataViewModel"
    private val _weatherDataFlowState: MutableStateFlow<UIState<WeatherData>> = MutableStateFlow(
        UIState.Loading)
val weatherDataFlowState : StateFlow<UIState<WeatherData>> = _weatherDataFlowState
    fun getWeatherData( lat: Double,lon: Double){
        viewModelScope.launch(Dispatchers.IO){
            try { val data=weatherDataRepository.getWeatherData(lat,lon)
                _weatherDataFlowState.value= UIState.Success(data)
            }catch (e: Exception){
                Log.d(TAG,e.toString())
            }


        }
    }

}