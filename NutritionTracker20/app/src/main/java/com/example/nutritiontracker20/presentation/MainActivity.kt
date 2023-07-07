package com.example.nutritiontracker20.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.nutritiontracker20.presentation.composeUI.graphs.RootNavigationGraph
import com.example.nutritiontracker20.presentation.composeUI.theme.NasaTema
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.presentation.viewmodels.MealViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mealViewModel: MealContract.ViewModel by viewModel<MealViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            NasaTema {
                val navController = rememberNavController()
                RootNavigationGraph(navController = navController, mealViewModel = mealViewModel)
            }
        }
    }
}