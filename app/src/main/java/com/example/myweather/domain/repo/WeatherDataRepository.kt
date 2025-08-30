package com.example.myweather.domain.repo

import com.example.myweather.domain.model.WeatherData
import com.example.myweather.utils.UIState


interface WeatherDataRepository {
    suspend fun getWeatherData( lat: Double, lon: Double):WeatherData
}