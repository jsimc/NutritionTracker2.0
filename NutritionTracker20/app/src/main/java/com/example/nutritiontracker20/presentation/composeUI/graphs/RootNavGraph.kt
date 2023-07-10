package com.example.nutritiontracker20.presentation.composeUI.graphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.presentation.contracts.UserContract
import com.example.nutritiontracker20.utils.INTRO_GRAPH
import com.example.nutritiontracker20.utils.ROOT_GRAPH

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    mealViewModel: MealContract.ViewModel,
    userViewModel: UserContract.ViewModel,
    paddingValues: PaddingValues
) {
    NavHost(
        navController = navController,
        route = ROOT_GRAPH,
        startDestination = INTRO_GRAPH, //INTRO_GRAPH
        modifier = Modifier.padding(paddingValues)
    ) {
        introGraph(navController = navController, userViewModel = userViewModel)
        mainGraph(navController = navController, mealViewModel = mealViewModel, userViewModel = userViewModel)
    }
}