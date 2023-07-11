package com.example.nutritiontracker20.data.models.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JCategory(

    val idCategory: Int,
    val strCategory: String,
    val strCategoryThumb: String, //url slike
    val strCategoryDescription: String
) {
}