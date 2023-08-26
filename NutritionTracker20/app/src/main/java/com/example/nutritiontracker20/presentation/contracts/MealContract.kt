package com.example.nutritiontracker20.presentation.contracts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nutritiontracker20.data.entities.SavedMealsEntity
import com.example.nutritiontracker20.data.entities.SavedMealsEntityWithCount
import com.example.nutritiontracker20.data.models.Category
import com.example.nutritiontracker20.data.models.Meal
import com.example.nutritiontracker20.data.models.Resource
import com.example.nutritiontracker20.data.models.domain.JIngredient
import com.example.nutritiontracker20.data.models.states.CategoriesState

interface MealContract {
    interface ViewModel {
        val chosenTopAppBar: LiveData<String>

        val mealsState: LiveData<Resource<List<Meal>>>
        val chosenMeal: LiveData<Resource<Meal>>

        val categoriesState: LiveData<CategoriesState>
        //promene potrebne ali neka stoji ovako chosenCategory da se ne bi crvenilo
        val chosenCategory: LiveData<Resource<Category>>

        val areasState: LiveData<Resource<List<String>>>
        val chosenArea: LiveData<Resource<String>>

        //!!! Koristim JIngredient umesto Ingredient jer nije istra struktura! ovo mi treba svakako samo za listanje i prikazivanje sa apija
        val ingredientsState: LiveData<Resource<List<JIngredient>>>
        val chosenIngredient: LiveData<Resource<JIngredient>>

        val savedMealState: LiveData<Boolean>
        val favoriteMealsState: LiveData<Resource<List<SavedMealsEntityWithCount>>>

        fun getMeals()
        fun getMealById(id: Int)
        fun getMealByName(name: String)
        fun getMealsByFirstLetter(letter: Char)
        fun filterMealsByCategory(category: String)
        fun filterMealsByArea(area: String)
        fun filterMealsByTags(tags: List<String>)

        fun getCategories()
        fun getAreas()
        fun getIngredients()

        fun filterMealsByIngredient(ingredient: String)
        fun search(searchBy: String) // moze da se odlozi pozivanje iz baze na nekoliko sekundi za ovaj search!
        fun searchMeal(searchBy: String)
        fun searchCategory(searchBy:String)
        // TODO rad sa podacima u view modelu
        // koja je razlika izmedju rx i obicnog ovog view modela?
    /////////////////////////////////////////////////////////////////////////////
        fun setKategorija(category: Category)
        fun setArea(area: String)
        fun setIngredient(ingredient: JIngredient)
    /////////////////////////////////////////////////////////////////////////////
        // SA BAZOM
        fun saveMealToFavorites(savedMealsEntity: SavedMealsEntity)
        fun getAllCountDescByName()
        fun getAllCountDescByCategory()
        fun getAllCountDescByArea()
    }
}
