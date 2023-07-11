package com.example.nutritiontracker20.data.models.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JIngredient(
    val idIngredient: Int,
    val strIngredient: String,
    val strDescription: String,
    val strType: String
)