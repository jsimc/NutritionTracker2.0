package com.example.nutritiontracker20.data.entities
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName =  "ingredientsInMeals",
        primaryKeys = ["mealId", "ingredientId"],
        foreignKeys = [
                ForeignKey(entity = SavedMealsEntity::class, parentColumns = ["id"], childColumns = ["mealId"]),
                ForeignKey(entity = IngredientsEntity::class, parentColumns = ["id"], childColumns = ["ingredientId"])
        ])
data class IngredientInMealEntity(
        val mealId: Long,
        val ingredientId: Long,
        val quantity: Int
) {

}