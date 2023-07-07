package com.example.nutritiontracker20.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nutritiontracker20.data.datasources.daos.IngredientDao
import com.example.nutritiontracker20.data.datasources.daos.IngredientInMealDao
import com.example.nutritiontracker20.data.datasources.daos.SavedMealDao
import com.example.nutritiontracker20.data.datasources.daos.UserDao
import com.example.nutritiontracker20.data.db.converters.DateConverter
import com.example.nutritiontracker20.data.models.IngredientInMealEntity
import com.example.nutritiontracker20.data.models.IngredientsEntity
import com.example.nutritiontracker20.data.models.SavedMealsEntity
import com.example.nutritiontracker20.data.models.UserEntity


//IngredientEntityTmp::class
@Database(
    entities = [UserEntity::class, SavedMealsEntity::class, IngredientsEntity::class, IngredientInMealEntity::class],
    version = 1, //OBAVEZNO KAD SE MENJA STRUKTURA BAZE MORA DA SE PROMENI VERZIJA SVAKI PUT PRE POKRETANJA PROJEKTA (praksa version += 1)
    exportSchema = false
)
//ovde konvertujemo komleksne u jednostavne tipove da bi se cuvali lepo u bazi - npr datum
//@TypeConverter()
@TypeConverters(DateConverter::class)
abstract class MealDatabase: RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getSavedMealDao(): SavedMealDao
    abstract fun getIngredientDao(): IngredientDao
    abstract fun getIngredientInMealDao(): IngredientInMealDao
}