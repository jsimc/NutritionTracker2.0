package com.example.nutritiontracker20.data.models.states

import com.example.nutritiontracker20.data.models.Category

sealed class CategoriesState {
    object Loading: CategoriesState()
    object DataFetched: CategoriesState()
    data class Success(val categories: List<Category>): CategoriesState()
    data class Error(val message: String): CategoriesState()
}
