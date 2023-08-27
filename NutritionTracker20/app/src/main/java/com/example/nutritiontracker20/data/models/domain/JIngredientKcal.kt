package com.example.nutritiontracker20.data.models.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class JIngredientKcal(
//    val id: Int,  nema na apiju
    val name : String,
    val calories: Float,
    val serving_size_g: Int, // mislim da je uvek 100 ali nisam sigurna
    //ovo sve dalje nam ni ne treba
    val fat_total_g: Float,
    val fat_saturated_g: Float,
    val protein_g: Float,
    val sodium_mg: Int,
    val potassium_mg: Int,
    val cholesterol_mg: Int,
    val carbohydrates_total_g: Float,
    val fiber_g: Float,
    val sugar_g: Float
    //TODO info sa drugog apija
)