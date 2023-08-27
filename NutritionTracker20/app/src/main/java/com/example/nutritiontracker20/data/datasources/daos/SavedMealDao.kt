package com.example.nutritiontracker20.data.datasources.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable
import com.example.nutritiontracker20.data.entities.SavedMealsEntity
import com.example.nutritiontracker20.data.entities.SavedMealsEntityWithCount

@Dao
abstract class SavedMealDao {


    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insertMeal(SavedMealsEntity: SavedMealsEntity): Completable

    @Query("SELECT * FROM saved_meals")
    abstract fun getAll(): Observable<List<SavedMealsEntity>>

    @Query("SELECT * FROM saved_meals WHERE id == :id")
    abstract fun getById(id: Long): SavedMealsEntity
    @Query("SELECT *, COUNT(*) AS count " +
            "FROM saved_meals " +
            "GROUP BY meal " +
            "ORDER BY count DESC;")
    abstract fun getAllCountDescByName(): Observable<List<SavedMealsEntityWithCount>>
    @Query("SELECT *, COUNT(*) AS count " +
            "FROM saved_meals " +
            "GROUP BY category " +
            "ORDER BY count DESC;")
    abstract fun getAllCountDescByCategory(): Observable<List<SavedMealsEntityWithCount>>

    @Query("SELECT *, COUNT(*) AS count " +
            "FROM saved_meals " +
            "GROUP BY region " +
            "ORDER BY count DESC;")
    abstract fun getAllCountDescByArea(): Observable<List<SavedMealsEntityWithCount>>

    @Query("SELECT * " +
            "FROM saved_meals " +
            "WHERE date >= strftime('%s', 'now', '-7 days') * 1000 " +
            "  AND date <= strftime('%s', 'now') * 1000")
    abstract fun getAllForGraph(): Observable<List<SavedMealsEntity>>
}