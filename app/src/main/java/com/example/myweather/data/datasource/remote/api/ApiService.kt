package com.example.myweather.data.datasource.remote.api


import com.example.myweather.domain.model.WeatherData
import com.example.myweather.utils.BASE_URL
import com.example.myweather.utils.UIState
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("weather")
    suspend fun getWeatherData(@Query("lat") lat: Double,
                               @Query("lon") lon: Double,
                               @Query("appid") appid: String): WeatherData

}