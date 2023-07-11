package com.example.nutritiontracker20.data.models

sealed class UserState {
    object Loading: UserState()
    object DataFetched: UserState()
    data class Success(val movies: List<User>): UserState()
    data class Error(val message: String): UserState()
}