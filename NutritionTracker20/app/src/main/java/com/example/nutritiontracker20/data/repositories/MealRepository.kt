package com.example.nutritiontracker20.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.entities.SavedMealsEntity
import com.example.nutritiontracker20.data.entities.SavedMealsEntityWithCount
import com.example.nutritiontracker20.data.models.Meal

// za Natu komentar:
// Ovo je mergovan pozivanje jela sa apija i iz baze (DBMealRepo i JMealRepo)
// nije moralo ovako, imali smo neki problem pa sam ga spojila jer sam mislila da je do toga
// sad me samo mrzi da vracam
interface MealRepository {
    //sa BAZOM
    fun insertMeal(SavedMealsEntity: SavedMealsEntity): Completable
    fun getAll(): Observable<List<SavedMealsEntity>>
    fun getById(id: Long): SavedMealsEntity
    fun getAllCountDescByName(): Observable<List<SavedMealsEntityWithCount>>
    fun getAllCountDescByCategory(): Observable<List<SavedMealsEntityWithCount>>
    fun getAllCountDescByArea(): Observable<List<SavedMealsEntityWithCount>>
    fun getAllForGraph(): Observable<List<SavedMealsEntity>>

    /////////////////////////////////////////////////////////////////////////////////
    // sa API
    fun getMeals(): Observable<List<Meal>>
    fun getMealById(id: Int): Observable<Meal>
    fun getMealByName(name: String): Observable<List<Meal>>
    fun getMealsByFirstLetter(letter: Char): Observable<List<Meal>>
    fun filterMealsByCategory(category: String): Observable<List<Meal>>
    fun filterMealsByArea(area: String): Observable<List<Meal>>
    fun filterMealsByIngredient(ingredient: String): Observable<List<Meal>>
}