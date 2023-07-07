package com.example.nutritiontracker20.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "saved_meals")
data class SavedMealsEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    val user : String,
    val meal : String,
    val kcal : Int,
    val tags : String?,
    val video : String,
    val photo : String,
    val category : String,
    val region : String,
    val instructions : String,
    //TODO da date bude onaj tip koji se vraca iz kalendara sa stranice za cuvanje jela
    @ColumnInfo(name = "date")val date: Date = Date()
    ) {
}
/*
data class IngredientEntity(
    //      @PrimaryKey(autoGenerate = true)
    val id : Long,
    val ingredientName : String,
    val amount : Int?,
    val kcal : Int?
)

 */