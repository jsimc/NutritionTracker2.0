package com.example.nutritiontracker20.presentation.composeUI.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nutritiontracker20.presentation.composeUI.screens.MainScreen
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.utils.INTRO_GRAPH
import com.example.nutritiontracker20.utils.MAIN_GRAPH
import com.example.nutritiontracker20.utils.ROOT_GRAPH

@Composable
fun RootNavigationGraph(navController: NavHostController, mealViewModel: MealContract.ViewModel) {
    NavHost(
        navController = navController,
        route = ROOT_GRAPH,
        startDestination = MAIN_GRAPH
    ) {
//        introGraph(navController = navController)
        composable(route = MAIN_GRAPH) {
            MainScreen(mealViewModel = mealViewModel)
        }
    }
}