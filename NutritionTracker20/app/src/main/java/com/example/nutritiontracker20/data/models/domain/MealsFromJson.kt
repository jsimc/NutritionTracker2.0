package com.example.nutritiontracker20.data.models.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MealsFromJson(
    @Json(name = "meals")
    val allMeals: List<JMeal>
) {}