package com.example.nutritiontracker20.data.repositories

import com.example.nutritiontracker20.data.models.domain.JIngredient
import io.reactivex.Observable

interface JIngredientRepository {
    fun getAllIngredients(): Observable<List<JIngredient>>
}