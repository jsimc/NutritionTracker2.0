package com.example.nutritiontracker20.data.datasources.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.entities.IngredientInMealEntity

@Dao
abstract class IngredientInMealDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insertIngredientInMeal(ingredientInMealEntity: IngredientInMealEntity): Completable

    @Query("SELECT * FROM ingredientsInMeals")
    abstract fun getAll(): Observable<List<IngredientInMealEntity>>

    @Query("SELECT * FROM ingredientsInMeals WHERE ingredientId == :id")
    abstract fun getByIngredientId(id: Long): IngredientInMealEntity


    @Query("SELECT * FROM ingredientsInMeals WHERE mealId == :id")
    abstract fun getByMealId(id: Long): IngredientInMealEntity

}