package com.example.nutritiontracker20.data.models.domain

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class JMeal(
    val idMeal: Int,
    val strMeal: String,
    val strDrinkAlternate: String?,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String, //veliki string
    val strMealThumb: String, //url slike
    val strTags: String?, //String je ali moze da ima vise vrednosti po modelu "tag1, tag2, tag3"
    val strYoutube: String, // link za video
    //zasto ovo nije niz stringova tho?????? :(
    //ako jelo ima npr 6 sastojaka svi dalje ce biti ""
// Dodala upitnike samo da moze i ne mora da postoji ! mozda ima bolji nacin ?
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: String?,
    val strIngredient17: String?,
    val strIngredient18: String?,
    val strIngredient19: String?,
    val strIngredient20: String?,
    //ima ih koliko i sastojaka, ako ih ima npr 6 svi dalje ce biti " "
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: String?,
    val strMeasure17: String?,
    val strMeasure18: String?,
    val strMeasure19: String?,
    val strMeasure20: String?,
    val strSource: String?, //ne znam sta je ovo iskreno link za neki peti sajt
    val strImageSource: String?,
    val strCreativeCommonsConfirmed: String?,
    val dateModified: String? //mozda nije svi su null

    )