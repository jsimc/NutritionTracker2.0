package com.example.nutritiontracker20.presentation.composeUI.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutritiontracker20.presentation.composeUI.elements.PlanGridView
import com.example.nutritiontracker20.presentation.contracts.PlanContract
import com.example.nutritiontracker20.utils.CREATE_PLAN_MODE
import com.example.nutritiontracker20.utils.HOME_PAGE


@Composable
fun CreatePlanScreen(planViewModel: PlanContract.ViewModel, navController: NavController) {
    val context = LocalContext.current

    val mealMap = planViewModel.weekMealsMap.observeAsState()

    Column {
        TopAppBar() {
            Text(text = "Create Plan")
        }
        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var email by remember { mutableStateOf("") }
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Email") })
                    PlanGridView(mealsMapa = mealMap.value) { chosenDayOfTheWeek, chosenMealOfTheDay ->
                        planViewModel.setChosenDayOfTheWeek(chosenDayOfTheWeek)
                        planViewModel.setChosenMealOfTheDay(chosenMealOfTheDay)
                        CREATE_PLAN_MODE = true
                        navController.navigate(HOME_PAGE)
                    }
                }
            }
            item {
                Button(onClick = { /*TODO posalji mejl*/
                    planViewModel.clearMap()
                }) {
                    Text(text = "Send mail")
                }
            }
        }
    }
}