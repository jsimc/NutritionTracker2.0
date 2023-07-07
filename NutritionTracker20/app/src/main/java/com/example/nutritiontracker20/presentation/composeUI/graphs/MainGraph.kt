package com.example.nutritiontracker20.presentation.composeUI.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nutritiontracker20.presentation.composeUI.screens.*
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.utils.*

@Composable
fun MainGraph(navController: NavHostController, mealViewModel: MealContract.ViewModel, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        route = MAIN_GRAPH,
        startDestination = HOME_PAGE,
        modifier= Modifier.padding(paddingValues)
    ) {
        composable(route = HOME_PAGE) {
            HomePage(mealViewModel = mealViewModel, navController = navController)
        }
        composable(route = MEALS_PAGE) {
            MealsPage(mealViewModel = mealViewModel, navController = navController)
        }
        composable(route = MEAL_DETAIL_PAGE) {
            MealDetailPage(navController = navController)
        }
        composable(route = SAVE_MEAL_SCREEN) {
            SaveMealScreen(navController = navController)
        }
        composable(route = PROFILE_SCREEN) {
            ProfileScreen()
        }
        composable(route = CREATE_PLAN_SCREEN) {
            CreatePlanScreen()
        }
    }
}