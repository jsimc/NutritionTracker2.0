package com.example.nutritiontracker20.data.datasources.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.entities.SavedMealsEntity

@Dao
abstract class SavedMealDao {


    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insertMeal(SavedMealsEntity: SavedMealsEntity): Completable

    @Query("SELECT * FROM saved_meals")
    abstract fun getAll(): Observable<List<SavedMealsEntity>>

    @Query("SELECT * FROM saved_meals WHERE id == :id")
    abstract fun getById(id: Long): SavedMealsEntity
}