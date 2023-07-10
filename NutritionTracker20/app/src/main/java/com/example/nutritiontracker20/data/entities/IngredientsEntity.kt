package com.example.nutritiontracker20.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientsEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Long,
    // @ColumnInfo(name = "meal_id") val mealId: Long,
    val ingredientName : String,
    //val amount : Int?, // da se prebaci u many to many
    val kcal : Int? //na 100g
        ){
}