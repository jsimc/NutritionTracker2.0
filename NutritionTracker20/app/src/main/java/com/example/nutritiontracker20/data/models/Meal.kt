package com.example.nutritiontracker20.data.models

data class Meal(
    val idMeal: Int,
    val strMeal: String = "",
    val strCategory: String = "",
    val strArea: String = "",
    val strInstructions: String = "", //veliki string
    val strMealThumb: String = "", //url slike
    val strTags: String = "",
    val strYoutube: String = "", // link za video
    // poyy ova mapa je nova
    val ingredients: Map<String, String> = mapOf()
)
