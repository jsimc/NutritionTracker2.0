package com.example.nutritiontracker20.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutritiontracker20.data.models.domain.JMeal
import com.example.nutritiontracker20.data.repositories.DBMealRepository
import com.example.nutritiontracker20.data.repositories.JMealRepository
import com.example.nutritiontracker20.presentation.contracts.MealContract

class MealViewModel(private val DBMealRepository: DBMealRepository
) : ViewModel(), MealContract.ViewModel {
    override val meals: LiveData<List<JMeal>>
        get() = TODO("Not yet implemented")
    override val JMeal: LiveData<JMeal>
        get() = TODO("Not yet implemented")

    override fun getMeals() {
        TODO("Not yet implemented")
    }

    override fun getMealByName(name: String) {
        TODO("Not yet implemented")
    }

    override fun getMealsByFirstLetter(letter: Char) {
        TODO("Not yet implemented")
    }

    override fun filterMealsByCategory(category: String) {
        TODO("Not yet implemented")
    }

    override fun filterMealsByArea(area: String) {
        TODO("Not yet implemented")
    }

    override fun filterMealsByIngredient(ingredient: String) {
        TODO("Not yet implemented")
    }

    override val chosenCategory: MutableLiveData<Any> = MutableLiveData()

    override fun search(searchBy: String) {
        TODO("Not yet implemented")
//        print(searchBy)
        //DEBOUNCE for waiting

    }

    override fun searchMeal(searchBy: String) {
        TODO("Not yet implemented")
    }

    override fun searchCategory(searchBy: String) {
        TODO("Not yet implemented")
    }

    override fun setKategorija() {
        TODO("Not yet implemented")
    }
}