package com.example.nutritiontracker20.presentation.composeUI.graphs

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.nutritiontracker20.data.helpers.UserLoginState
import com.example.nutritiontracker20.presentation.composeUI.screens.LoginScreen
import com.example.nutritiontracker20.presentation.contracts.UserContract
import com.example.nutritiontracker20.utils.INTRO_GRAPH
import com.example.nutritiontracker20.utils.LOGIN_SCREEN
import com.example.nutritiontracker20.utils.MAIN_GRAPH


fun NavGraphBuilder.introGraph(navController: NavController, userViewModel: UserContract.ViewModel) {
    navigation(startDestination = LOGIN_SCREEN, route = INTRO_GRAPH) {
        composable(route = LOGIN_SCREEN){
            val context = LocalContext.current
            val sharedPreferences: SharedPreferences = context.getSharedPreferences("loggedIn", Context.MODE_PRIVATE)
            val loggedIn: Boolean = sharedPreferences.getBoolean("user_logged_in", false)

            if(!loggedIn) {
                LoginScreen(navController = navController, userViewModel = userViewModel)
            } else {
                navController.popBackStack()
                navController.navigate(MAIN_GRAPH)
            }
        }
    }
}