package com.example.nutritiontracker20.data.datasources.remote

import io.reactivex.Observable
import com.example.nutritiontracker20.data.models.domain.JMeal
import retrofit2.http.GET
import retrofit2.http.Path

interface MealService {

    @GET("search.php?s")
    fun getMeals(): Observable<List<JMeal>>

    @GET("search.php?s={name}")
    fun getMealByName(@Path("name") name: String): Observable<JMeal>

    @GET("search.php?f={letter}")
    fun getMealsByFirstLetter(@Path("letter") letter: Char): Observable<List<JMeal>>

    @GET("filter.php?c={category}")
    fun filterMealsByCategory(@Path("category") category: String): Observable<List<JMeal>>

    @GET("filter.php?a={area}")
    fun filterMealsByArea(@Path("area") area: String): Observable<List<JMeal>>

    @GET("filter.php?i={ingredient}")
    fun filterMealsByIngredient(@Path("ingredient") ingredient: String): Observable<List<JMeal>>

}