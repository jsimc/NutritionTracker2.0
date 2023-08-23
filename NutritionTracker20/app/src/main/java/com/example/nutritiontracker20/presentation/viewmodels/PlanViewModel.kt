package com.example.nutritiontracker20.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutritiontracker20.data.models.Meal
import com.example.nutritiontracker20.data.models.MealPlan
import com.example.nutritiontracker20.presentation.contracts.PlanContract
import com.example.nutritiontracker20.utils.eMealOfTheDay
import java.time.DayOfWeek

class PlanViewModel() : ViewModel(), PlanContract.ViewModel {
    override val weekMealsMap: MutableLiveData<MutableMap<String, MealPlan>> = MutableLiveData(mutableMapOf()) //kontam ovo prazna mapa
    override val chosenDayOfTheWeek: MutableLiveData<DayOfWeek> = MutableLiveData()
    override val chosenMealOfTheDay: MutableLiveData<eMealOfTheDay> = MutableLiveData()


    override fun setChosenDayOfTheWeek(dayOfTheWeek: DayOfWeek) {
        chosenDayOfTheWeek.value = dayOfTheWeek
        Log.d("PlanViewModel", "setChosenDayOfTheWeek: ${chosenDayOfTheWeek.value}")
    }

    override fun setChosenMealOfTheDay(mealOfTheDay: eMealOfTheDay) {
        chosenMealOfTheDay.value = mealOfTheDay
        Log.d("PlanViewModel", "setChosenMealOfTheDay ${chosenMealOfTheDay.value}")
    }

    override fun setMeal(meal: Meal) {
        weekMealsMap.value!![chosenDayOfTheWeek.value!!.name] = MealPlan(chosenDayOfTheWeek.value!!, chosenMealOfTheDay.value!!, meal)
        Log.d("PlanViewModel", "setMeal: ${weekMealsMap.value}")
    }

    override fun clearMap() {
        weekMealsMap.value = mutableMapOf()
    }
}