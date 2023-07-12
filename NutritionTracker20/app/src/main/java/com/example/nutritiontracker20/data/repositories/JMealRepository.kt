package com.example.nutritiontracker20.data.repositories

import io.reactivex.Observable
import com.example.nutritiontracker20.data.models.Meal
import com.example.nutritiontracker20.data.models.domain.JMeal

interface JMealRepository {
    fun getMeals(): Observable<List<Meal>>
    fun getMealByName(name: String): Observable<Meal>
    fun getMealsByFirstLetter(letter: Char): Observable<List<Meal>>
    fun filterMealsByCategory(category: String): Observable<List<Meal>>
    fun filterMealsByArea(area: String): Observable<List<Meal>>
    fun filterMealsByIngredient(ingredient: String): Observable<List<Meal>>

}