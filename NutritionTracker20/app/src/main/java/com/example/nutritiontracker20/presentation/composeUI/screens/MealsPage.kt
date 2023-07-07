package com.example.nutritiontracker20.presentation.composeUI.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nutritiontracker20.presentation.composeUI.elements.MealListView
import com.example.nutritiontracker20.presentation.composeUI.elements.SearchBar
import com.example.nutritiontracker20.presentation.contracts.MealContract

@Composable
fun MealsPage(mealViewModel: MealContract.ViewModel, navController: NavController) {
    Column() {
        TopAppBar {
            Text (text = "Kategorija 1") //prosledjeno -> kategorija.name
        }
        SearchBar(onSearch = {mealViewModel.searchMeal(it)})
        LazyColumn(modifier = Modifier.padding(14.dp),
            horizontalAlignment = Alignment.End, // End
            verticalArrangement = Arrangement.Center)
        {
            items(10) {
                MealListView(mealViewModel, navController, it)
            }
        }
    }
}