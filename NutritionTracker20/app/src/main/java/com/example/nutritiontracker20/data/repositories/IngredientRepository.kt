package com.example.nutritiontracker20.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.models.IngredientsEntity

interface IngredientRepository {
    fun insertIngredient(IngredientsEntity: IngredientsEntity): Completable
    fun getAll(): Observable<List<IngredientsEntity>>
    fun getById(id: Long): IngredientsEntity
}