package com.example.nutritiontracker20.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nutritiontracker20.utils.eActivity
import com.example.nutritiontracker20.utils.eGender

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    var username : String,
    var password : String,
    var age : Int?,
    var height : Int?,
    var weight : Int?,
    var gender : eGender?,
    var weeklyActivity: eActivity?,
    var suggestedKcal : Int?,

    ) {

}