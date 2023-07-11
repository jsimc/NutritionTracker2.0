package com.example.nutritiontracker20.data.models.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class JIngredientKcal(
    val id: Int
    //TODO info sa drugog apija
)