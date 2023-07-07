package com.example.nutritiontracker20.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.datasources.daos.IngredientInMealDao
import com.example.nutritiontracker20.data.models.IngredientInMealEntity
import com.example.nutritiontracker20.data.repositories.IngredientInMealRepository

class IngredientInMealRepositoryImpl(private val ingredientInMealDao: IngredientInMealDao) :
    IngredientInMealRepository {
    override fun insertIngredient(IngredientInMealEntity: IngredientInMealEntity): Completable {
        return ingredientInMealDao.insertIngredientInMeal(IngredientInMealEntity)
    }

    override fun getAll(): Observable<List<IngredientInMealEntity>> {
        return ingredientInMealDao.getAll()
    }

    override fun getByIngredientId(id: Long): IngredientInMealEntity {
        return ingredientInMealDao.getByIngredientId(id)
    }

    override fun getByMealId(id: Long): IngredientInMealEntity {
        return ingredientInMealDao.getByMealId(id)

    }
}