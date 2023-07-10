package com.example.nutritiontracker20.data.models

import com.example.nutritiontracker20.utils.eActivity
import com.example.nutritiontracker20.utils.eGender

data class User(
    var username: String,
    var password: String,
    var age: Int? = null,
    var height: Int? = null,
    var weight: Int? = null,
    var gender: eGender? = null,
    var weeklyActivity: eActivity? = null,
    var suggestedKcal: Int? = null
)