package com.example.nutritiontracker20.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.entities.IngredientInMealEntity

interface IngredientInMealRepository {
    fun insertIngredient(IngredientInMealEntity: IngredientInMealEntity): Completable
    fun getAll(): Observable<List<IngredientInMealEntity>>
    fun getByIngredientId(id: Long): IngredientInMealEntity
    fun getByMealId(id: Long): IngredientInMealEntity

}