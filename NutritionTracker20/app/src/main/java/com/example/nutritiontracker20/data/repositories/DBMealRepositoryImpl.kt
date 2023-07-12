package com.example.nutritiontracker20.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.datasources.daos.SavedMealDao
import com.example.nutritiontracker20.data.entities.SavedMealsEntity

class DBMealRepositoryImpl(private val savedMealDao: SavedMealDao) : DBMealRepository {
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