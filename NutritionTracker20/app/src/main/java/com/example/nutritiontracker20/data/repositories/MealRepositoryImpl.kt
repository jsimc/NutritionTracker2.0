package com.example.nutritiontracker20.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.datasources.daos.SavedMealDao
import com.example.nutritiontracker20.data.models.SavedMealsEntity
import com.example.nutritiontracker20.data.repositories.MealRepository

class MealRepositoryImpl(private val savedMealDao: SavedMealDao) : MealRepository {
    override fun insertMeal(savedMealsEntity: SavedMealsEntity): Completable {
        return savedMealDao.insertMeal(savedMealsEntity)
    }

    override fun getAll(): Observable<List<SavedMealsEntity>> {
        return  savedMealDao.getAll()
    }

    override fun getById(id: Long): SavedMealsEntity {
        return savedMealDao.getById(id)
    }
}