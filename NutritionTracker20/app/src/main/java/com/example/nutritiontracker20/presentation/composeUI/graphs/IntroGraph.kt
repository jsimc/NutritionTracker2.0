package com.example.nutritiontracker20.presentation.composeUI.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.nutritiontracker20.presentation.composeUI.screens.MySplashScreen
import com.example.nutritiontracker20.utils.INTRO_GRAPH
import com.example.nutritiontracker20.utils.SPLASH_SCREEN


fun NavGraphBuilder.introGraph(navController: NavController) {
    navigation(startDestination = SPLASH_SCREEN, route = INTRO_GRAPH) {
        composable(route = SPLASH_SCREEN){
            MySplashScreen(navController = navController)
        }
    }
}