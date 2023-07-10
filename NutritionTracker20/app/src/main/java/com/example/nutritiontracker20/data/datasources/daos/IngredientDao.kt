package com.example.nutritiontracker20.data.datasources.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.entities.IngredientsEntity

@Dao
abstract class IngredientDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insertIngredient(ingredientsEntity: IngredientsEntity): Completable

    @Query("SELECT * FROM ingredients")
    abstract fun getAll(): Observable<List<IngredientsEntity>>

    @Query("SELECT * FROM ingredients WHERE id == :id")
    abstract fun getById(id: Long): IngredientsEntity
}