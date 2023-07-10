package com.example.nutritiontracker20.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.entities.SavedMealsEntity

interface MealRepository {
    //TODO ubaciti funkcije za dobavljanje iz repositorija // Nata ce to? hoce hoce
    fun insertMeal(SavedMealsEntity: SavedMealsEntity): Completable
    fun getAll(): Observable<List<SavedMealsEntity>>
    fun getById(id: Long): SavedMealsEntity
}