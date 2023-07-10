package com.example.nutritiontracker20.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.datasources.daos.IngredientDao
import com.example.nutritiontracker20.data.entities.IngredientsEntity

class IngredientRepositoryImpl(private val ingredientDao: IngredientDao) : IngredientRepository {
    override fun insertIngredient(IngredientsEntity: IngredientsEntity): Completable {
        return ingredientDao.insertIngredient(IngredientsEntity)
    }

    override fun getAll(): Observable<List<IngredientsEntity>> {
        return ingredientDao.getAll()
    }

    override fun getById(id: Long): IngredientsEntity {
        return ingredientDao.getById(id)
    }

}