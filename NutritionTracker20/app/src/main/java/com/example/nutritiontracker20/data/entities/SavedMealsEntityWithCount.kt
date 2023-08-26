package com.example.nutritiontracker20.data.entities

import androidx.room.Embedded

data class SavedMealsEntityWithCount(
    @Embedded val savedMeal: SavedMealsEntity,
    val count: Int
)