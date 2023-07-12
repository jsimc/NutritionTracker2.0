package com.example.nutritiontracker20.data.repositories

import io.reactivex.Observable
import com.example.nutritiontracker20.data.datasources.remote.MealService
import com.example.nutritiontracker20.data.models.Meal
import com.example.nutritiontracker20.data.models.domain.JMeal

class JMealRepositoryImpl(private val mealService: MealService) : JMealRepository {

//    val idMeal: Int,
//    val strMeal: String,
//    val strDrinkAlternate: String,
//    val strCategory: String,
//    val strArea: String,
//    val strInstructions: String, //veliki string
//    val strMealThumb: String, //url slike
//    val strTags: String

//    val idMeal: Int,
//    val strMeal: String,
//    val strDrinkAlternate: String,
//    val strCategory: String,
//    val strArea: String,
//    val strInstructions: String, //veliki string
//    val strMealThumb: String, //url slike
//    val strTags: String, //String je ali moze da ima vise vrednosti po modelu "tag1, tag2, tag3"
//    val strYoutube: String, // link za video

    override fun getMeals(): Observable<List<Meal>> {
        return mealService
            .getMeals()
            .map {
                it.map { jMeal: JMeal ->
                    Meal(
                        idMeal = jMeal.idMeal,
                        strMeal = jMeal.strMeal,
                        strDrinkAlternate = jMeal.strDrinkAlternate,
                        strCategory =  jMeal.strCategory,
                        strArea = jMeal.strArea,
                        strInstructions = jMeal.strInstructions,
                        strMealThumb = jMeal.strMealThumb,
                        strTags = jMeal.strTags,
                        strYoutube = jMeal.strYoutube
                    )


                }

            }
    }

    override fun getMealByName(name: String): Observable<Meal> {
        return mealService
            .getMealByName(name)
            .map{jMeal->
                Meal(
                    idMeal = jMeal.idMeal,
                    strMeal = jMeal.strMeal,
                    strDrinkAlternate = jMeal.strDrinkAlternate,
                    strCategory =  jMeal.strCategory,
                    strArea = jMeal.strArea,
                    strInstructions = jMeal.strInstructions,
                    strMealThumb = jMeal.strMealThumb,
                    strTags = jMeal.strTags,
                    strYoutube = jMeal.strYoutube

                )

            }

    }

    override fun getMealsByFirstLetter(letter: Char): Observable<List<Meal>> {
        return mealService
            .getMealsByFirstLetter(letter)
            .map{
                it.map { jMeal ->
                    Meal(
                        idMeal = jMeal.idMeal,
                        strMeal = jMeal.strMeal,
                        strDrinkAlternate = jMeal.strDrinkAlternate,
                        strCategory = jMeal.strCategory,
                        strArea = jMeal.strArea,
                        strInstructions = jMeal.strInstructions,
                        strMealThumb = jMeal.strMealThumb,
                        strTags = jMeal.strTags,
                        strYoutube = jMeal.strYoutube
                    )
                }
            }
    }

    override fun filterMealsByCategory(category: String): Observable<List<Meal>> {
        return mealService
            .filterMealsByCategory(category)
            .map{
                it.map{jMeal->
                    Meal(
                        idMeal = jMeal.idMeal,
                        strMeal = jMeal.strMeal,
                        strDrinkAlternate = jMeal.strDrinkAlternate,
                        strCategory = jMeal.strCategory,
                        strArea = jMeal.strArea,
                        strInstructions = jMeal.strInstructions,
                        strMealThumb = jMeal.strMealThumb,
                        strTags = jMeal.strTags,
                        strYoutube = jMeal.strYoutube
                    )

                }
            }
    }

    override fun filterMealsByArea(area: String): Observable<List<Meal>> {
        return mealService
            .filterMealsByCategory(area)
            .map{
                it.map{jMeal->
                    Meal(
                        idMeal = jMeal.idMeal,
                        strMeal = jMeal.strMeal,
                        strDrinkAlternate = jMeal.strDrinkAlternate,
                        strCategory = jMeal.strCategory,
                        strArea = jMeal.strArea,
                        strInstructions = jMeal.strInstructions,
                        strMealThumb = jMeal.strMealThumb,
                        strTags = jMeal.strTags,
                        strYoutube = jMeal.strYoutube
                    )

                }
            }
    }

    override fun filterMealsByIngredient(ingredient: String): Observable<List<Meal>> {
        return mealService
            .filterMealsByCategory(ingredient)
            .map{
                it.map{jMeal->
                    Meal(
                        idMeal = jMeal.idMeal,
                        strMeal = jMeal.strMeal,
                        strDrinkAlternate = jMeal.strDrinkAlternate,
                        strCategory = jMeal.strCategory,
                        strArea = jMeal.strArea,
                        strInstructions = jMeal.strInstructions,
                        strMealThumb = jMeal.strMealThumb,
                        strTags = jMeal.strTags,
                        strYoutube = jMeal.strYoutube
                    )

                }
            }
    }
}