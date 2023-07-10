package com.example.nutritiontracker20.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.nutritiontracker20.presentation.composeUI.elements.MyBottomNavigation
import com.example.nutritiontracker20.presentation.composeUI.graphs.RootNavigationGraph
import com.example.nutritiontracker20.presentation.composeUI.theme.NasaTema
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.presentation.contracts.UserContract
import com.example.nutritiontracker20.presentation.viewmodels.MealViewModel
import com.example.nutritiontracker20.presentation.viewmodels.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mealViewModel: MealContract.ViewModel by viewModel<MealViewModel>()
    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()
//    private val userLoginState: UserLoginState = get()
//    private val sharedPreferences: SharedPreferences = getSharedPreferences("loggedIn", Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()
        setContent {
            NasaTema {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { MyBottomNavigation(navController = navController) }
                ) { paddingValues ->
//                    MainGraph(navController = navController, mealViewModel = mealViewModel, userViewModel = userViewModel, paddingValues = paddingValues)
                    RootNavigationGraph(navController = navController, mealViewModel = mealViewModel, userViewModel = userViewModel, paddingValues = paddingValues)
                }
            }
        }
    }
}