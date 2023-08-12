package com.example.nutritiontracker20.data.models.domain

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class JListIngredients(
    @Json(name = "meals")
    val allIngredients: List<JIngredient>
){}