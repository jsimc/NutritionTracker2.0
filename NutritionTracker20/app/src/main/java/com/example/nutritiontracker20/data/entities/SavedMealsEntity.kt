package com.example.nutritiontracker20.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "saved_meals")
data class SavedMealsEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    val user : String,
//    val idMeal : Int, //TODO ???
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
//    constructor(
//        user: String,
//        meal: String,
//        kcal: Int,
//        tags: String?,
//        video: String,
//        photo: String,
//        category: String,
//        region: String,
//        instructions: String) : this(0, user, meal, kcal, tags, video, photo, category, region, instructions)
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