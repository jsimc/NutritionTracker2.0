package com.example.nutritiontracker20.presentation.contracts

import androidx.lifecycle.LiveData
import com.example.nutritiontracker20.data.models.Meal
import com.example.nutritiontracker20.data.models.MealPlan
import com.example.nutritiontracker20.utils.eMealOfTheDay
import java.time.DayOfWeek

interface PlanContract {
    interface ViewModel {
        val chosenDayOfTheWeek: LiveData<DayOfWeek>
        val chosenMealOfTheDay: LiveData<eMealOfTheDay>
        val weekMealsMap: LiveData<MutableMap<String, MealPlan>>

        fun setChosenDayOfTheWeek(dayOfTheWeek: DayOfWeek)
        fun setChosenMealOfTheDay(mealOfTheDay: eMealOfTheDay)
        fun setMeal(meal: Meal)

        fun clearMap()
    }
}