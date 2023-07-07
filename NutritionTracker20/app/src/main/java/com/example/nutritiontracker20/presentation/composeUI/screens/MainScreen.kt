package com.example.nutritiontracker20.presentation.composeUI.screens

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.nutritiontracker20.presentation.composeUI.elements.MyBottomNavigation
import com.example.nutritiontracker20.presentation.composeUI.graphs.MainGraph
import com.example.nutritiontracker20.presentation.contracts.MealContract

@Composable
fun MainScreen(navController: NavHostController = rememberNavController(), mealViewModel: MealContract.ViewModel) {
    Scaffold(
        bottomBar = { MyBottomNavigation(navController = navController) }
    ) { paddingValues ->
        MainGraph(navController = navController, mealViewModel = mealViewModel, paddingValues = paddingValues)
    }
}