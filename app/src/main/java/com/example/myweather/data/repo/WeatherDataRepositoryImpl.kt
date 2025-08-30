package com.example.myweather.data.repo

import android.util.Log
import com.example.myweather.data.datasource.remote.api.ApiService
import com.example.myweather.domain.model.WeatherData
import com.example.myweather.domain.repo.WeatherDataRepository
import com.example.myweather.utils.API_KEY
import com.example.myweather.utils.UIState
import javax.inject.Inject

class WeatherDataRepositoryImpl @Inject constructor (private val apiService: ApiService): WeatherDataRepository {
    private  val TAG = "WeatherDataRepositoryIm"

    override suspend fun getWeatherData(
        lat: Double,
        lon: Double
    ): WeatherData {
        return apiService.getWeatherData(lat, lon, API_KEY)



    }
}