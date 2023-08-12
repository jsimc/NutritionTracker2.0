package com.example.nutritiontracker20.data.repositories

import com.example.nutritiontracker20.data.datasources.remote.IngredientService
import com.example.nutritiontracker20.data.models.domain.JIngredient
import io.reactivex.Observable

class JIngredientRepositoryImpl(
    private val ingredientService: IngredientService
): JIngredientRepository{
    override fun getAllIngredients(): Observable<List<JIngredient>> {
        return ingredientService
            .getAllIngredients()
            .map {
                it.allIngredients.map { jIngredient ->
                    JIngredient(
                        idIngredient = jIngredient.idIngredient,
                        strIngredient = jIngredient.strIngredient ?: "No info",
                        strDescription = jIngredient.strDescription ?: "No info",
                        strType = jIngredient.strType ?: "No info"
                    )
                }
            }
    }
}