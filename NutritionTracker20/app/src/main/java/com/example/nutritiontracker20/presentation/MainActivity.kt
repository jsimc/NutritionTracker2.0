package com.example.nutritiontracker20.presentation

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.nutritiontracker20.data.models.User
import com.example.nutritiontracker20.presentation.composeUI.elements.MyBottomNavigation
import com.example.nutritiontracker20.presentation.composeUI.graphs.RootNavigationGraph
import com.example.nutritiontracker20.presentation.composeUI.theme.NasaTema
import com.example.nutritiontracker20.presentation.contracts.MealContract
import com.example.nutritiontracker20.presentation.contracts.UserContract
import com.example.nutritiontracker20.presentation.viewmodels.MealViewModel
import com.example.nutritiontracker20.presentation.viewmodels.UserViewModel
import com.example.nutritiontracker20.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mealViewModel: MealContract.ViewModel by viewModel<MealViewModel>()
    private val userViewModel: UserContract.ViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        installSplashScreen()
        setContent {
            NasaTema {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = { MyBottomNavigation(navController = navController) }
                ) { paddingValues ->
                    RootNavigationGraph(navController = navController, mealViewModel = mealViewModel, userViewModel = userViewModel, paddingValues = paddingValues)
                }
            }
        }
    }

    private fun init() {
        val defaultUser = User(
            username = DEFAULT_USERNAME,
            password = DEFAULT_PASSWORD,
            age = DEFAULT_AGE,
            height = DEFAULT_HEIGHT,
            weight = DEFAULT_WEIGHT,
            gender = DEFAULT_GENDER,
            weeklyActivity = DEFAULT_WEEKLY_ACTIVITY
        )
        defaultUser.suggestedKcal = kcalCalculator(defaultUser.gender!!, defaultUser.height!!, defaultUser.weight!!, defaultUser.age!!)
        // e sad da li ce ove dve inser i setLogged da se atomicno pozovu ili jok?
        userViewModel.insertUser(defaultUser)
        userViewModel.setUser(defaultUser)
        val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences("loggedIn", MODE_PRIVATE)
//        sharedPreferences.edit().clear().apply()
        val edit = sharedPreferences.edit()
        edit.putBoolean("user_logged_in", true)
        edit.putString("username", defaultUser.username)
        edit.putString("password", defaultUser.password)
        edit.apply()
    }


}