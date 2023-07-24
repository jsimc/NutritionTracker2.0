package com.example.nutritiontracker20.data.models.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JMealFilterByCategory(
    val idMeal: Int,
    val strMeal: String,
    val strMealThumb: String
) {
}