package com.example.myweather.utils

sealed class UIState< out T> {
    data class Success<T>(val data:T): UIState<T>()
    data class  Error<T>(val error: T): UIState<T>()
    object Loading : UIState<Nothing>()


}