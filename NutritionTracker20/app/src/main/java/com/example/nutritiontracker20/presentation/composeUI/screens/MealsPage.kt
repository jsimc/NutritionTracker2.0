package com.example.nutritiontracker20.presentation.composeUI.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutritiontracker20.data.models.Resource
import com.example.nutritiontracker20.presentation.composeUI.elements.MealListView
import com.example.nutritiontracker20.presentation.composeUI.elements.SearchBar
import com.example.nutritiontracker20.presentation.contracts.MealContract

@Composable
fun MealsPage(mealViewModel: MealContract.ViewModel, navController: NavController) {
    var txt by remember { mutableStateOf("") }

    val chosenCategory = mealViewModel.chosenCategory.observeAsState()
    val chosenArea = mealViewModel.chosenArea.observeAsState()
    val chosenIngredient = mealViewModel.chosenIngredient.observeAsState()
    val mealsState = mealViewModel.mealsState.observeAsState(Resource.Loading())


    LaunchedEffect(key1 = chosenCategory, key2 = chosenArea, key3 = chosenIngredient) {
        if (chosenCategory.value != null) {
            mealViewModel.filterMealsByCategory(chosenCategory.value!!.strCategory)
            txt = chosenCategory.value!!.strCategory
        }
        else if (chosenArea.value != null) {
            mealViewModel.filterMealsByArea((chosenArea.value as Resource.Success).data)
            txt = (chosenArea.value as Resource.Success).data
        }
        else if (chosenIngredient.value != null) {
            mealViewModel.filterMealsByIngredient((chosenIngredient.value as Resource.Success).data.strIngredient!!)
            txt = (chosenIngredient.value as Resource.Success).data.strIngredient.toString()
        }
    }
    Column() {
        // naziv: Kategorije, Oblasti, Sastojka
        TopAppBar {
            Text (text = txt)
        }
        SearchBar(onSearch = {mealViewModel.searchMeal(it)}) //TODO ovo treba izmeniti i u HOME PAGE
        LazyColumn(modifier = Modifier
            .padding(14.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally, // End
            verticalArrangement = Arrangement.Center)
        {
            item {
                when (mealsState.value) {
                    is Resource.Loading -> { CircularProgressIndicator() }
                    is Resource.Success -> {
                        for (meal in (mealsState.value as Resource.Success).data) {
                            MealListView(mealViewModel = mealViewModel, navController = navController, meal = meal, onClick = {mealViewModel.getMealById(meal.idMeal)})
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(LocalContext.current, (mealsState.value as Resource.Error).error.message, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}