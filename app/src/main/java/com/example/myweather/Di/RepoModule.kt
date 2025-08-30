package com.example.myweather.Di

import com.example.myweather.data.datasource.remote.api.ApiService
import com.example.myweather.data.repo.WeatherDataRepositoryImpl
import com.example.myweather.domain.repo.WeatherDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepoModule {
    @Provides
    fun providerWeatherDataRepo(apiService: ApiService): WeatherDataRepository=
        WeatherDataRepositoryImpl(apiService)

}