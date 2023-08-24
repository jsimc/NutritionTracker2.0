package com.example.nutritiontracker20.utils

import com.example.nutritiontracker20.data.entities.SavedMealsEntity
import java.time.Instant
import java.util.*

var CREATE_PLAN_MODE = false
//------------------------------------------------------
const val HOME_PAGE = "home_page"
const val SPLASH_SCREEN = "splash_screen"
const val MEALS_PAGE = "meals_page"
const val MEAL_DETAIL_PAGE = "meal_detail_page"
const val SAVE_MEAL_SCREEN = "save_meal_screen"
const val PROFILE_SCREEN = "profile_screen"
const val CREATE_PLAN_SCREEN = "create_plan_screen"
const val LOGIN_SCREEN = "login_screen"
//------------------------------------------------------
const val MAIN_GRAPH = "main_graph"
const val INTRO_GRAPH = "intro_graph"
const val ROOT_GRAPH = "root_graph"
//------------------------------------------------------
const val DEFAULT_USERNAME = "nata"
const val DEFAULT_PASSWORD = "1312"
const val DEFAULT_AGE = 21
const val DEFAULT_HEIGHT = 150
const val DEFAULT_WEIGHT = 7
val DEFAULT_GENDER = eGender.FEMALE
val DEFAULT_WEEKLY_ACTIVITY = eActivity.ThreeOrFourTimes
//------------------------------------------------------
//var CAMERA_PERMISSION = true
val SAVED_MEALS = listOf<SavedMealsEntity>(
    SavedMealsEntity(0L, "nata", "jelo1", 120, "", "", "", "", "", "",
        Date.from(Instant.now())))
//------------------------------------------------------
const val CATEGORY = "Categories"
const val AREA = "Areas"
const val INGREDIENT = "Ingredients"
const val MEAL = "Meals"
//------------------------------------------------------
val TAGS = listOf(
        "Meat", "Treat", "Soup", "Streetfood", "Onthego", "SideDish", "Curry", "Vegetarian" ,"Cake", "MainMeal", "UnHealthy", "Speciality",
        "HangoverFood", "Snack", "Savory", "Breakfast", "Fish", "Pie", "Baking", "Tart", "Desert",
        "Sweet", "Fruity", "Dairy", "LowCalorie", "Pudding", "Bun", "Stew", "Warming", "Alcoholic",
        "Spicy", "Calorific"
    )
//------------------------------------------------------
val WEEKDAYS = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
