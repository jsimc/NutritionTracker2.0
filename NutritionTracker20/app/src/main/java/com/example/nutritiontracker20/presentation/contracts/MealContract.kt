package com.example.nutritiontracker20.presentation.contracts

import androidx.lifecycle.LiveData
import com.example.nutritiontracker20.data.models.Category
import com.example.nutritiontracker20.data.models.Meal
import com.example.nutritiontracker20.data.models.Resource
import com.example.nutritiontracker20.data.models.states.CategoriesState

interface MealContract {
    interface ViewModel {

        val mealsState: LiveData<Resource<List<Meal>>>
        val chosenMeal: LiveData<Resource<Meal>>

        val categoriesState: LiveData<CategoriesState>
        //promene potrebne ali neka stoji ovako chosenCategory da se ne bi crvenilo
        val chosenCategory: LiveData<Category>
        fun getMeals()
        fun getMealById(id: Int)
        fun getMealByName(name: String)
        fun getMealsByFirstLetter(letter: Char)
        fun filterMealsByCategory(category: String)
        fun filterMealsByArea(area: String)

        fun filterMealsByIngredient(ingredient: String)
        fun search(searchBy: String) // moze da se odlozi pozivanje iz baze na nekoliko sekundi za ovaj search!
        fun setKategorija(category: Category)
        fun searchMeal(searchBy: String)
        fun searchCategory(searchBy:String)
        // TODO rad sa podacima u view modelu
        // koja je razlika izmedju rx i obicnog ovog view modela?
    }
}
