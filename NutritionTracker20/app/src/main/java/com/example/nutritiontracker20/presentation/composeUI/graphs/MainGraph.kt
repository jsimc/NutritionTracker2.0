package com.example.nutritiontracker20.presentation.composeUI.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.nutritiontracker20.presentation.composeUI.screens.*
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.presentation.contracts.PlanContract
import com.example.nutritiontracker20.presentation.contracts.UserContract
import com.example.nutritiontracker20.utils.*
fun NavGraphBuilder.mainGraph(
    navController: NavController,
    mealViewModel: MealContract.ViewModel,
    userViewModel: UserContract.ViewModel,
    planViewModel: PlanContract.ViewModel
) {
    navigation(startDestination = HOME_PAGE, route = MAIN_GRAPH) {
        composable(route = HOME_PAGE) {
            HomePage(mealViewModel = mealViewModel, planViewModel = planViewModel, navController = navController)
        }
        composable(route = MEALS_PAGE) {
            MealsPage(mealViewModel = mealViewModel, planViewModel = planViewModel, navController = navController)
        }
        composable(route = MEAL_DETAIL_PAGE) {
            MealDetailPage(mealViewModel = mealViewModel, navController = navController)
        }
        composable(route = SAVE_MEAL_SCREEN) {
            SaveMealScreen(mealViewModel = mealViewModel, userViewModel = userViewModel, navController = navController)
        }
        composable(route = PROFILE_SCREEN) {
            ProfileScreen(userViewModel = userViewModel, navController = navController)
        }
        composable(route = CREATE_PLAN_SCREEN) {
            CreatePlanScreen(planViewModel = planViewModel, navController = navController)
        }
        composable(route = FAVORITES_SCREEN) {
            FavoritesScreen(mealViewModel = mealViewModel)
        }
    }
}