package com.example.nutritiontracker20.data.models

import com.example.nutritiontracker20.utils.eMealOfTheDay
import java.time.DayOfWeek

data class MealPlan(
    private val dayOfTheWeek: DayOfWeek, // mon, tue, ... , sat, sun
    val mealOfTheDay: eMealOfTheDay, // breakfast, lunch, dinner
    private val meal: Meal //koje je jelo u pitanju
)